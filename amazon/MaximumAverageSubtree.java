package oa.amazon;

import utils.TreeNode;

public class MaximumAverageSubtree {

    public TreeNode findMaxAverageSubtree(TreeNode root) {
        MaxAveSubtreeWrapper wrapper = helper(root);

        return wrapper.node;
    }

    /* This method doesn't accept leaf node as result */
    private MaxAveSubtreeWrapper helper(TreeNode node) {
        /* Base case: If null, return */
        if (node == null) {
            return new MaxAveSubtreeWrapper(null, 0, 0);
        }

        /* Base case: If leaf node, return null because leaf node is not accepted */
        if (node.left == null && node.right == null) {
            return new MaxAveSubtreeWrapper(null, node.val, 1);
        }

        /* If not leaf node, get left and right children and compare average */
        MaxAveSubtreeWrapper wrapperLeft = helper(node.left);
        MaxAveSubtreeWrapper wrapperRight = helper(node.right);

        double aveLeft = Double.NEGATIVE_INFINITY;
        if (wrapperLeft.node != null) {
            aveLeft = ((double) wrapperLeft.sum) / wrapperLeft.cnt;
        }

        double aveRight = Double.NEGATIVE_INFINITY;
        if (wrapperRight.node != null) {
            aveRight = ((double) wrapperRight.sum) / wrapperRight.cnt;
        }

        int sumThis = wrapperLeft.sum + wrapperRight.sum + node.val;
        int cntThis = wrapperLeft.cnt + wrapperRight.cnt + 1;
        double aveThis = ((double) sumThis) / cntThis;

        double maxAve;
        TreeNode maxAveNode;
        int sum;
        int cnt;

        if (wrapperLeft.node != null && aveLeft > aveRight) {
            maxAve = aveLeft;
            maxAveNode = wrapperLeft.node;
            sum = wrapperLeft.sum;
            cnt = wrapperLeft.cnt;
        } else {
            maxAve = aveRight;
            maxAveNode = wrapperRight.node;
            sum = wrapperRight.sum;
            cnt = wrapperRight.cnt;
        }

        if (maxAve < aveThis) {
            maxAve = aveThis;
            maxAveNode = node;
            sum = sumThis;
            cnt = cntThis;
        }

        return new MaxAveSubtreeWrapper(maxAveNode, sum, cnt);
    }

    class MaxAveSubtreeWrapper {
        TreeNode node;
        int sum;
        int cnt;

        MaxAveSubtreeWrapper(TreeNode n, int s, int c) {
            node = n;
            sum = s;
            cnt = c;
        }
    }

}
