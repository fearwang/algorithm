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
        if (args[0].equals("TreeDepth")) {
            new Jz38_TreeDepth().solute(solutionArgs);
        }
        if (args[0].equals("IsBalancedTree")) {
            new Jz39_IsBalancedTree().solute(solutionArgs);
        }
        if (args[0].equals("LastRemaining")) {
            new Jz46_LastRemaining().solute(solutionArgs);
        }
        if (args[0].equals("DeleteDuplication")) {
            new Jz56_deleteDuplication().solute(solutionArgs);
        }
        return 0;
    }
}