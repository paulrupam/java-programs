import java.util.Scanner;
class MagicSquare
{
    int n,a[][];
    public MagicSquare(int x)
    {
        n=x;
        a=new int[x][x];
    }
    public void generation(int a[][])
    {
        int i = n/2,j = n-1;
        for (int num=1; num <= n*n; )
        {
            if (i==-1 && j==n)
            {
                j = n-2;
                i = 0;
            }
            else
            {
                if (j == n)
                    j = 0;
                if (i < 0)
                    i=n-1;
            }
            if (a[i][j] != 0)  
            {
                j -= 2;
                i++;
                continue;
            }
            else
                a[i][j] = num++;  
            j++;  i--;  
        }
    }
    public void generation1()
    {
        for (int j = 0; j < n; j++)
        {
            for (int i = 0; i < n; i++)
            {
               if (((i+1)/2)%2 == ((j+1)/2)%2)
               {
                  a[i][j] = n*n-n*i-j;
               }
               else
               {
                  a[i][j] = n*i+j+1;
               }
            }
         }
    }
    public void generation2(int[][] magicsqr)
    {

        int N = magicsqr.length;
        int halfN = N/2; //size of ABCD boxes
        int k = (N-2)/4; // to get 'noses' of A & D boxes
        int temp;

        int [] swapCol = new int[N]; // columns which need to swap between C-B & A-D
        int index=0; // index of swapCol

        int [][] miniMagic =  new int [halfN][halfN];
        generation(miniMagic); //creating odd magic square for A box
    
        //creating 4 magic boxes
        for (int i=0; i<halfN; i++)
            for (int j=0; j<halfN; j++)
            {
                magicsqr[i][j] = miniMagic[i][j];               //A box
                magicsqr[i+halfN][j+halfN] = miniMagic[i][j]+halfN*halfN;   //B box
                magicsqr[i][j+halfN] = miniMagic[i][j]+2*halfN*halfN;       //C box
                magicsqr[i+halfN][j] = miniMagic[i][j]+3*halfN*halfN;       //D box
            }
        for (int i=1; i<=k; i++)
            swapCol[index++] = i;

        for (int i=N-k+2; i<=N; i++)
            swapCol[index++] = i;

        //swaping values between C-B & A-D by known columns
        for (int i=1; i<=halfN; i++)
            for (int j=1; j<=index; j++)
            {
                temp=magicsqr[i-1][swapCol[j-1]-1];
                magicsqr[i-1][swapCol[j-1]-1]=magicsqr[i+halfN-1][swapCol[j-1]-1];
                magicsqr[i+halfN-1][swapCol[j-1]-1]=temp;
            }

        //swaping noses
        temp=magicsqr[k][0]; 
        magicsqr[k][0]=magicsqr[k+halfN][0]; 
        magicsqr[k+halfN][0]=temp;


        temp=magicsqr[k+halfN][k]; 
        magicsqr[k+halfN][k]=magicsqr[k][k]; 
        magicsqr[k][k]=temp;
        //end of swaping

    }
    public void print()
    {
        System.out.println("The Magic Square Matrix is");
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
    public static void main()
    {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter the dimension of the magic square matrix you want to generate:");
        int x=s.nextInt();
        MagicSquare o = new MagicSquare(x);
        if(x%2!=0)
            generation(o.a);
        else if(x%4==0)
            o.generation1();
        else
            o.generation2();
        o.print();
    }
}