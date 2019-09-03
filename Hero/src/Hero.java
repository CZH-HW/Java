// 使用同一个包下的其他类，直接使用即可 ；但是要使用其他包下的类，必须import
//import java.util.Scanner;
//import java.util.Arrays;

public class Hero {
	
	/*
	public String name; //实例属性，对象属性，非静态属性
    protected float hp;
    static String copyright;//类属性，静态属性，所有对象都共享一个值
	*/
	
	public String name;
    protected float hp;
  
    private static void battleWin(){
        System.out.println("battle win");
    }
     
    //敌方的水晶
    static class EnemyCrystal{
        int hp=5000;
         
        //如果水晶的血量为0，则宣布胜利
        public void checkIfVictory(){
            if(hp==0){
                Hero.battleWin();
                 
                //静态内部类不能直接访问外部类的对象属性
                System.out.println(name + " win this game");
            }
        }
    }
     
	
	
	
	
	/*
    String name; //姓名    
    float hp; //血量     
    float armor; //护甲    
    int moveSpeed; //移动速度
	*/
	
    
    /*
	//参数名和属性名一样
	//在方法体中，只能访问到参数name
	public void setName1(String name){
		name = name;
	}

	//为了避免setName1中的问题，参数名不得不使用其他变量名
	public void setName2(String heroName){
		name = heroName;
	}

	//通过this访问属性
	public void setName3(String name){
		//name代表的是参数name
		//this.name代表的是属性name
		this.name = name;
	}
	*/
	

	
    /*
    //打印内存中的虚拟地址
    public void showAddressInMemory(){
        System.out.println("打印this看到的虚拟地址："+this);
    }
    */
    
    /*
    //带一个参数的构造方法
    public Hero(String name){
        System.out.println("一个参数的构造方法");
        this.name = name;
    }
      
    //带两个参数的构造方法
    public Hero(String name,float hp){
        this(name);
        System.out.println("两个参数的构造方法");
        this.hp = hp;
    }
    */

    /* 
    public Hero(String name,float hp){
        this.name = name;
        this.hp = hp;
    }
 
    //复活
    public void revive(Hero h){
        h = new Hero("提莫",383);
    }
    */
					
	public static void main(String[] arg){
		/*
		Hero  h =new Hero();

		h.setName1("teemo");
		System.out.println(h.name);

		h.setName2("garen");
		System.out.println(h.name);

		h.setName3("死歌");
		System.out.println(h.name);
		*/
		
		
		Hero.EnemyCrystal crystal = new Hero.EnemyCrystal();
        crystal.checkIfVictory();
		
		
		
		/*
		Hero garen =  new Hero();
        garen.name = "盖伦";
         
        Hero.copyright = "版权由Riot Games公司所有";
         
        System.out.println(garen.name);
        System.out.println(garen.copyright);
         
        Hero teemo =  new Hero();
        teemo.name = "提莫";
        System.out.println(teemo.name);    
        System.out.println(teemo.copyright);
	 	*/
			
			
		
		/*
		Hero garen =  new Hero();
        garen.name = "盖伦";
        //直接打印对象，会显示该对象在内存中的虚拟地址
        //格式：Hero@c17164 c17164即虚拟地址，每次执行，得到的地址不一定一样
 
        System.out.println("打印对象看到的虚拟地址："+garen);
        //调用showAddressInMemory，打印该对象的this，显示相同的虚拟地址
        garen.showAddressInMemory();
         
        Hero teemo =  new Hero();
        teemo.name = "提莫";
        System.out.println("打印对象看到的虚拟地址："+teemo);
        teemo.showAddressInMemory();	
	    */
					
					
		/*
		// 二维数据排序
		// 首先定义一个5X8的二维数组，然后使用随机数填充满。
        // 借助Arrays的方法对二维数组进行排序。

        int[][] arrays = new int[5][8];
 
        // 使用0-100的随机数将二维数组填充
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                arrays[i][j] = (int) (Math.random() * 100);
            }
        }
 
        // 打印排序前的数组
        System.out.println("排序前的数组为:");
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print("[" + i + "]" + "[" + j + "]=" + arrays[i][j] + "\t");
            }
            System.out.println();
        }
 
        // 创建一维标记数组,用来存放二维数组
        int[] mark = new int[arrays.length * arrays[0].length]; 
        for (int i = 0; i < arrays.length; i++) {
            // 将二维数组存放到标记数组mark里      	
            System.arraycopy(arrays[i], 0, mark, arrays[i].length * i, arrays[i].length);
        }
         
        // 对标记数组进行排序
        Arrays.sort(mark);
        System.out.println(Arrays.toString(mark));
        
        for (int i = 0; i < arrays.length; i++) {
        	arrays[i] = Arrays.copyOfRange(mark, i*arrays.length, (i+1)*arrays.length);
            //System.arraycopy(mark, arrays[i].length * i, arrays[i], 0, arrays[i].length);
        }
 
        // 打印排序后的数组
        System.out.println("排序后的数组为:");
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print("[" + i + "]" + "[" + j + "]=" + arrays[i][j] + "\t");
            }
            System.out.println();
        }
		*/
	
		
		/*
		// Arrays类中的复制数组的方法copyOfRange()
		int a[] = new int[] { 18, 62, 68, 82, 65, 9 };
		
		// 转化为字符串
		String content = Arrays.toString(a);
		System.out.println(content);
		
		 
        // copyOfRange(int[] original, int from, int to)
        // 第一个参数表示源数组
        // 第二个参数表示开始位置(取得到)
        // 第三个参数表示结束位置(取不到)
        int[] b = Arrays.copyOfRange(a, 0, 3);
 
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
		*/
		
		
		
		/*
		int[][] a = new int[5][5];
		for(int i=0; i<5; i++){
			for(int j=0; j<a[0].length; j++){
				a[i][j] = (int)(Math.random()*100);
			}
		}

		int max = 0;
		int max_I = 0;
		int max_J = 0;
		for(int i=0; i<5; i++){
			for(int j=0; j<a[0].length; j++){
				if(max<a[i][j]){
					max = a[i][j];
					max_I = i;
					max_J = j;
				}
			}
		}
			
		System.out.println("找出的最大值为：" + max);
		System.out.println("其坐标是：" + "[" + max_I +"]" + "[" + max_J + "]");
		*/
	
		
		/*
		// 首先准备两个数组，他俩的长度是5-10之间的随机数，并使用随机数初始化这两个数组
		int length1 = (int) (Math.random() * 5) + 5; // 数组1长度为5-10的随机数
		int length2 = (int) (Math.random() * 5) + 5; // 数组2长度为5-10的随机数
		int[] array1 = new int[length1];
		int[] array2 = new int[length2];
		int[] array3 = new int[length1 + length2]; // 数组3的长度为数组1和数组2长度之和
		
		for (int i = 0; i < array1.length; i++) {
			array1[i] = (int) (Math.random() * 100); // 数组1里各数的大小为0-100之间的随机数
		}
		
		for (int i = 0; i < array2.length; i++) {
			array2[i] = (int) (Math.random() * 100); // 数组21里各数的大小为0-100之间的随机数
		}

		// 打印数组1
		System.out.println("当前数组1为:");
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i] + " ");
		}
		
		// 打印数组2
		System.out.println("");
		System.out.println("当前数组2为:");
		for (int i = 0; i < array2.length; i++) {
			System.out.print(array2[i] + " ");
		}
		
		System.out.println("");
		System.arraycopy(array1, 0, array3, 0, length1); // 将数组1合并到数组3里
		System.arraycopy(array2, 0, array3, length1, length2);// 将数组21合并到数组3里
		System.out.println("合并后的数组为:");
				
		// 打印数组3
		for (int i = 0; i < array3.length; i++) {
			System.out.print(array3[i] + " ");
		}
		*/
				
		
		/*
		//写法一： 分配空间同时赋值，如果指定了数组的内容，就不能同时设置数组的长度
        int[] a = new int[]{100,102,444,836,3236};
 
        //写法二： 省略了new int[],效果一样
        int[] b = {100,102,444,836,3236};
		*/
		
		
		/*
		int[] a = new int[5];
		
		System.out.println("数组中的各个随机数是:");
		for(int i=0; i<a.length; i++){
			a[i] = (int)(Math.random()*100);
			System.out.println(a[i]);
			
		}
		
		int min = a[0];
		for(int i=1; i<a.length; i++){
			if(min>a[i]){
				min = a[i];
			}
		}
		
		System.out.println("数组中最小的数是：" + min);
		*/
		
				
		/*
		int i = 1;
		i+=++i;
		System.out.println(i); //输出结果为3		
		*/
		
		
		/*
		int i = 3; // 二进制是11
		int j = 2; // 二进制是10
		int c = ((i | j) ^ (i & j)) << 2 >>> 1;
		System.out.println(c);
		*/
		
			
		/*
		int i = 5;
        String b = Integer.toBinaryString(i); // 十进制转换为二进制的表达
        System.out.println(i+" 的二进制表达是: " + b);
		*/		
		
		/*
		Scanner compare = new Scanner(System.in);
		int firstInt = compare.nextInt();
		System.out.println("第一个整数：" + firstInt);
		int secondInt = compare.nextInt();
		System.out.println("第一个整数：" + secondInt);
		System.out.println("比较"+firstInt+">"+secondInt+":"+(firstInt>secondInt));
        System.out.println("比较"+firstInt+">="+secondInt+":"+(firstInt>=secondInt));
        System.out.println("比较"+firstInt+"<"+secondInt+":"+(firstInt<secondInt));
        System.out.println("比较"+firstInt+"<="+secondInt+":"+(firstInt<=secondInt));
        System.out.println("比较"+firstInt+"=="+secondInt+":"+(firstInt==secondInt));
        System.out.println("比较"+firstInt+"!="+secondInt+":"+(firstInt!=secondInt));
		*/
		
		
		/*
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		System.out.println("第一个整数：" + a);
		int b = s.nextInt();
		System.out.println("第二个整数：" + b);
		int sum1 = a + b;
		System.out.println("两个整数之和：" + sum1);
		 */
		
		
		/*
		int i = 1;
		int j = ++i + i++ + ++i + ++i + i++;
		System.out.println(j);
		*/
		
		
		/* 
		Scanner sc=new Scanner(System.in);
        System.out.println("请输入身高,单位为m");  
        float height=sc.nextFloat();
        System.out.println("请输入体重，单位为kg");
        float weight=sc.nextFloat();
        float BMI = (float)(weight/(height*height));
        if(BMI<18.5)
            System.out.println("体重过轻");
        else if(BMI<24)
            System.out.println("体重正常");
        else if(BMI<27)
            System.out.println("体重过重");
        else if(BMI<30)
            System.out.println("轻度肥胖");
        else if(BMI<35)
            System.out.println("中度肥胖");
        else
            System.out.println("重度肥胖");
        sc.close();
        */
				
		
	}

}
