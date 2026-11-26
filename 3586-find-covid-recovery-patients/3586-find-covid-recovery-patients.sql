# Write your MySQL query statement below
WITH FirstPositive AS (
    SELECT patient_id, MIN(test_date) AS first_pos_date
    FROM covid_tests
    WHERE result = 'Positive'
    GROUP BY patient_id
)
SELECT 
    p.patient_id, 
    p.patient_name, 
    p.age, 
    DATEDIFF(MIN(c.test_date), fp.first_pos_date) AS recovery_time
FROM patients p
JOIN FirstPositive fp 
    ON p.patient_id = fp.patient_id
JOIN covid_tests c 
    ON c.patient_id = fp.patient_id 
   AND c.result = 'Negative' 
   AND c.test_date > fp.first_pos_date
GROUP BY 
    p.patient_id, 
    p.patient_name, 
    p.age, 
    fp.first_pos_date
ORDER BY 
    recovery_time ASC, 
    p.patient_name ASC;
