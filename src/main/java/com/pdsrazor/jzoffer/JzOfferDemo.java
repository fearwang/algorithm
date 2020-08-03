package com.pdsrazor.jzoffer;

import com.pdsrazor.algo.MyDemo;

public class JzOfferDemo extends MyDemo {    
    @Override
    public void usage() {
        System.out.println("JzOfferDemo:");
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
        System.out.println("  GetNumberOfK");
        System.out.println("  FindNumsAppearOnce");
        System.out.println("  duplicate");
        System.out.println("  isNumeric");
    }

    @Override
    public int doCmd(String args[]) {
        String[] solutionArgs = new String[args.length-1];
        for (int i = 0; i < args.length - 1; i++) {
            solutionArgs[i] = args[i+1];
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
        if (args[0].equals("LastRemaining")) {
            new Jz46_LastRemaining().solute(solutionArgs);
        }
        if (args[0].equals("duplicate")) {
            new Jz50_duplicate().solute(solutionArgs);
        }
        if (args[0].equals("isNumeric")) {
            new Jz53_isNumeric().solute(solutionArgs);
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
        return 0;
    }
}