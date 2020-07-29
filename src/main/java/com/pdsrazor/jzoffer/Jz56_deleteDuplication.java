package com.pdsrazor.jzoffer;

public class Jz56_deleteDuplication extends Solution {
    @Override
    void solute(String[] args) {
        ListNode node = new ListNode(1);
        ListNode head = node;

        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);
        node = node.next;

        //print(deleteDuplication(head));
        print(deleteDuplication2(head));
    }

    void print(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + "->");
            p = p.next;
        }
        System.out.println();
    }

    class ListNode {
        int val;
        ListNode next = null;
    
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        
        ListNode pSingle = null;
        ListNode pSame = null;
        ListNode pRet = null;
        ListNode p = pHead;
        ListNode pp = pHead.next;
        
        while (pp != null) {
            if (p.val == pp.val) {
                pSame = p;
                p.next = pp.next;
                pp = p.next;
            } else {
                if (pSame != null) {
                    if (pSingle != null) pSingle.next = pSame.next;
                    pSame = null;
                    p = pp;
                    pp = pp.next;
                } else {
                    pSingle = p;
                    if (pRet == null) { pRet = pSingle;}
                    p = pp;
                    pp = pp.next;
                }
                
            }
        }
        if (pSame == null && pSingle == null) pRet = p;
        if (pSame != null && pSingle != null) pSingle.next = pSame.next; 
        
        return pRet;
    }

    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        
        ListNode pSingle = null; // 不存在重复节点部分的尾节点
        ListNode pSameStart = null; // 重复区间的起点节点
        ListNode pRet = null; // 返回的头节点
        ListNode p = pHead; // 遍历
        
        while (p.next != null) {
            if (p.val == p.next.val) { // 如果和下一个节点相同，则标注相同区间的起点
                if (pSameStart == null) pSameStart = p;
            } else {
                 // p指向了当前相同区间的结束节点（此区间包含p）
                if (pSameStart != null) {
                    pSameStart = null; // reset 相同区间
                    if (pSingle != null) pSingle.next = p.next; // 不重复区间的尾节点指向 当前相同区间的下一个节点
                } else {
                    // p指向的节点不是相同区间的结束节点，且和下一个节点值不同，意味着p没有重复节点，
                    pSingle = p; // 更新不重复区间
                    if (pRet == null) pRet = p;
                }
            }
            p = p.next;
        }
        // 如果pSingle空，代表没有遇到不重复的节点，此时如果pSameStart为空，表示最后一个节点以外，其他节点都重复，返回最后一个节点
        if (pSingle == null && pSameStart == null) return p;

        // 如果最后的一些节点全部重复，那么退出循环的时候，需要删除掉尾部的节点。
        if (pSameStart != null && pSingle != null) pSingle.next = p.next;
     
        // 如果所有节点重复，那么返回null
        return pRet;
    }
}