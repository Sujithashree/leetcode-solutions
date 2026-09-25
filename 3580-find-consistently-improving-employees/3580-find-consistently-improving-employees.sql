WITH ranked AS (
    SELECT
        employee_id,
        review_date,
        rating,
        ROW_NUMBER() OVER (
            PARTITION BY employee_id
            ORDER BY review_date DESC
        ) AS rn
    FROM performance_reviews
),
last_three AS (
    SELECT
        employee_id,
        rating,
        rn
    FROM ranked
    WHERE rn <= 3
)
SELECT
    e.employee_id,
    e.name,
    MAX(l.rating) - MIN(l.rating) AS improvement_score
FROM employees e
JOIN last_three l
    ON e.employee_id = l.employee_id
GROUP BY
    e.employee_id,
    e.name
HAVING
    COUNT(*) = 3
    AND MAX(CASE WHEN l.rn = 3 THEN l.rating END)
        < MAX(CASE WHEN l.rn = 2 THEN l.rating END)
    AND MAX(CASE WHEN l.rn = 2 THEN l.rating END)
        < MAX(CASE WHEN l.rn = 1 THEN l.rating END)
ORDER BY
    improvement_score DESC,
    e.name ASC;