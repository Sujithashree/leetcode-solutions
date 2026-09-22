class Solution {
    static class Node {
        int[] cnt;
        int prod;

        Node(int k) {
            cnt = new int[k];
            prod = 1 % k;
        }
    }

    int k;
    int[] nums;
    Node[] tree;

    Node merge(Node a, Node b) {
        Node res = new Node(k);

        // Prefixes completely inside the left segment.
        for (int r = 0; r < k; r++) {
            res.cnt[r] += a.cnt[r];
        }

        // Prefixes that take the whole left segment
        // and then some prefix of the right segment.
        for (int r = 0; r < k; r++) {
            int newRem = (int)((long)a.prod * r % k);
            res.cnt[newRem] += b.cnt[r];
        }

        // Product of the complete segment.
        res.prod = (int)((long)a.prod * b.prod % k);

        return res;
    }

    Node makeNode(int value) {
        Node node = new Node(k);

        int rem = value % k;

        // The only non-empty prefix is the element itself.
        node.cnt[rem] = 1;
        node.prod = rem;

        return node;
    }

    void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = makeNode(nums[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    void update(int idx, int l, int r, int pos, int value) {
        if (l == r) {
            tree[idx] = makeNode(value);
            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, value);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, value);
        }

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(idx * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(idx * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int index = queries[qi][0];
            int value = queries[qi][1];
            int start = queries[qi][2];
            int x = queries[qi][3];

            // Persistent update.
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // We need all possible remaining arrays after
            // removing a suffix, starting from `start`.
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[qi] = res.cnt[x];
        }

        return ans;
    }
}