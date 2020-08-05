package com.pdsrazor.jzoffer;

public class Jz35_InversePairs extends Solution {
    @Override
    void solute(String args[]) {
        int arr[] = {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,
            418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,
            557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,
            497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,
            735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575};
        
        int arr2[] = {627126,415347,850134,371085,279048,705820,453064,944751,92317,58592,167988,
            284065,992573,78043,190215,104546,607528,391775,701214,849731,231053,603058,975374,
            199773,479544,143961,206797,325662,90035,69615,429916,717161,484962,796403,604598,
            280362,502223,57662,741466,594540,632606,909454,394957,625180,503849,585172,729726,
            627729,976947,947293,477461,724352,66703,452835,440478,62599,596797,163627,388261,
            203184,233243,334529,436697,234557,647284,41295,514920,665859,615310,256386,776752,
            247916,682192,171709,389448,186041,273234,635527,813771,766533,582820,807584,490886,
            649523,260419,447716,228474,373568,611343,616735,576752,844586,467616,529801,595496,
            631253,571097,110416,297112,186407,883154,73864,950675,81698,245574,340124,267739,
            35160,975651,597862,801693,74823,921798,292579,240698,182218,256647,469172,72138,
            867991,602259,165243,228929,69875,695044,824425,701128,782493,451193,998241,485252,
            334347,588457,435928,416045,350383,292404,200137,385543,268055,314351,187237,859230,
            236150,996168,99928,9347};


        System.out.print("reverse: " + InversePairs(arr2));
        //System.out.print("reverseBF: " + InversePairsBF(arr2));
    }

    int [] marr = null;
    int reverse = 0;
    public int InversePairs(int [] array) {
        if (array == null || array.length <= 1) return 0;
        int start = 0;
        int end = array.length-1;
        marr = new int[array.length];
        reverse = 0;
        divide(array, start, end);
        return reverse;
    }
    
    public void divide(int[] array, int start, int end) {
        if (start < end) {
            int mid = start + (end-start)/2;
            divide(array, start, mid);
            divide(array, mid+1, end);
            merge(array, start, mid, end);
        }
    }
    
    public void merge(int[] array, int start, int mid, int end) {
        int i = mid;
        int j = end;
        int k = end;
        while (i >= start && j >= (mid+1)) {
            if (array[i] > array[j]) {
                reverse = reverse + (j-mid);
                if (reverse > 1000000007) reverse = reverse % 1000000007;
                marr[k--] = array[i];
                i--;
            } else if (array[i] < array[j]) {
                marr[k--] = array[j];
                j--;
            }
        }
        
        while (i >= start) {
            marr[k--] = array[i--];
        }
        
        while (j >= (mid+1)) {
            marr[k--] = array[j--];
        }
        
        for (int m = start; m <= end; m++) {
            array[m] = marr[m];
        }
    }

    public int InversePairsBF(int [] array) {
        int ret = 0;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) ret++;
            }
        }
        return ret;
    }
}