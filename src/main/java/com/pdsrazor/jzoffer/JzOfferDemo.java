package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.MyDemo;

public class JzOfferDemo extends MyDemo {    
    @Override
    public void usage() {
        System.out.println("JzOfferDemo:");
        System.out.println("  Find");
        System.out.println("  MoreThanHalfNum");
        System.out.println("  GetLeastNumbers <k>");
        System.out.println("  TreeDepth <size>");
        System.out.println("  IsBalancedTree <size>");
        System.out.println("  LastRemaining <nchild> <mCount>");
        System.out.println("  DeleteDuplication");
        System.out.println("  GetNextForInOrder <size>");
        System.out.println("  isSymmetrical <size>");
        System.out.println("  PrintLevel <size>");
        System.out.println("  PrintZ <size>");
        System.out.println("  SerializeTree <size>");
        System.out.println("  MidData");
        System.out.println("  KthNodeInBST <size> <k>");
        System.out.println("  FindGreatestSumOfSubArray");
        System.out.println("  NumberOf1");
        System.out.println("  GetNumberOfK");
        System.out.println("  FindNumsAppearOnce");
        System.out.println("  duplicate");
        System.out.println("  isNumeric");
        System.out.println("  FirstNotRepeatingChar");
        System.out.println("  FirstNotRepeatCharInStream");
        System.out.println("  isContinuous");
        System.out.println("  FindContinuousSequence");
        System.out.println("  FindNumbersWithSum");
        System.out.println("  InversePairs");
        System.out.println("  maxInWindows");
        System.out.println("  movingCount");
        System.out.println("  hasPath");
        System.out.println("  RegMatch");
        System.out.println("  PrintMinNumber");
        System.out.println("  cutRope");
        System.out.println("  uglyNumber");
    }

    @Override
    public int doCmd(String args[]) {
        String[] solutionArgs = new String[args.length-1];
        for (int i = 0; i < args.length - 1; i++) {
            solutionArgs[i] = args[i+1];
        }
        if (args[0].equals("Find")) {
            new Jz1_find().solute(solutionArgs);
        }
        if (args[0].equals("JumpFloor")) {
            new Jz8_JumpFloor().solute(solutionArgs);
        }
        if (args[0].equals("MoreThanHalfNum")) {
            new Jz28_MoreThanHalfNum().solute(solutionArgs);
        }
        if (args[0].equals("GetLeastNumbers")) {
            new Jz29_GetLeastNumbers().solute(solutionArgs);
        }
        if (args[0].equals("FindGreatestSumOfSubArray")) {
            new Jz30_FindGreatestSumOfSubArray().solute(solutionArgs);
        }
        if (args[0].equals("NumberOf1")) {
            new Jz31_NumberOf1().solute(solutionArgs);
        }
        if (args[0].equals("PrintMinNumber")) {
            new Jz32_PrintMinNumber().solute(solutionArgs);
        }
        if (args[0].equals("uglyNumber")) {
            new Jz33_UglyNumber().solute(solutionArgs);
        }
        if (args[0].equals("FirstNotRepeatingChar")) {
            new Jz34_FirstNotRepeatingChar().solute(solutionArgs);
        }
        if (args[0].equals("InversePairs")) {
            new Jz35_InversePairs().solute(solutionArgs);
        }
        if (args[0].equals("GetNumberOfK")) {
            new Jz37_GetNumberOfK().solute(solutionArgs);
        }
        if (args[0].equals("TreeDepth")) {
            new Jz38_TreeDepth().solute(solutionArgs);
        }
        if (args[0].equals("IsBalancedTree")) {
            new Jz39_IsBalancedTree().solute(solutionArgs);
        }
        if (args[0].equals("FindNumsAppearOnce")) {
            new Jz40_FindNumsAppearOnce().solute(solutionArgs);
        }
        if (args[0].equals("FindContinuousSequence")) {
            new Jz41_FindContinuousSequence().solute(solutionArgs);
        }
        if (args[0].equals("FindNumbersWithSum")) {
            new Jz42_FindNumbersWithSum().solute(solutionArgs);
        }
        if (args[0].equals("isContinuous")) {
            new Jz45_isContinuous().solute(solutionArgs);
        }
        if (args[0].equals("LastRemaining")) {
            new Jz46_LastRemaining().solute(solutionArgs);
        }
        if (args[0].equals("duplicate")) {
            new Jz50_duplicate().solute(solutionArgs);
        }
        if (args[0].equals("RegMatch")) {
            new Jz52_RegMatch().solute(solutionArgs);
        }
        if (args[0].equals("isNumeric")) {
            new Jz53_isNumeric().solute(solutionArgs);
        }
        if (args[0].equals("FirstNotRepeatCharInStream")) {
            new Jz54_FirstNotRepeatCharInStream().solute(solutionArgs);
        }
        if (args[0].equals("DeleteDuplication")) {
            new Jz56_deleteDuplication().solute(solutionArgs);
        }
        if (args[0].equals("GetNextForInOrder")) {
            new Jz57_NextNodeForInOrder().solute(solutionArgs);
        }
        if (args[0].equals("isSymmetrical")) {
            new Jz58_isSymmetrical().solute(solutionArgs);
        }
        if (args[0].equals("PrintLevel")) {
            new Jz60_PrintLevel().solute(solutionArgs);
        }
        if (args[0].equals("PrintZ")) {
            new Jz59_PrintZ().solute(solutionArgs);
        }
        if (args[0].equals("SerializeTree")) {
            new Jz61_SerializeTree().solute(solutionArgs);
        }
        if (args[0].equals("KthNodeInBST")) {
            new Jz62_KthNodeInBST().solute(solutionArgs);
        }
        if (args[0].equals("MidData")) {
            new Jz63_MidData().solute(solutionArgs);
        }
        if (args[0].equals("maxInWindows")) {
            new Jz64_maxInWindows().solute(solutionArgs);
        }
        if (args[0].equals("hasPath")) {
            new Jz65_hasPath().solute(solutionArgs);
        }
        if (args[0].equals("movingCount")) {
            new Jz66_movingCount().solute(solutionArgs);
        }
        if (args[0].equals("cutRope")) {
            new Jz67_cutRope().solute(solutionArgs);
        }
        return 0;
    }
}