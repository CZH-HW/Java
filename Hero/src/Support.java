// 方法的重载指的是方法名一样，但是参数类型不一样
// Java允许在一个类中定义多个名称相同的方法，但是参数的类型或个数必须不同，这就是方法的重载。

class Hero2 {     
    String name; //姓名     
    float hp; //血量    
    float armor; //护甲     
    int moveSpeed; //移动速度
}


public class Support extends Hero2{
	
	public void heal(){
		System.out.println(name + "为英雄进行了治疗");
	}
	
	public void heal(Hero2 h1, int hp){
		System.out.println(name + "为英雄加了" + hp + "的血");
	}
	
	public void heal(Hero2...heros){
		for(int i=0; i<heros.length; i++){
			System.out.println(name + "治疗了" + heros[i].name);
		}
	}
	
	public static void main(String[] args){
		Hero2 garen = new Hero2();
	    garen.name = "盖伦";
	    Hero2 timo = new Hero2();
	    timo.name = "提莫";
	     
	    Support shengmu = new Support();
	    shengmu.name = "圣母";
	    
	    shengmu.heal();
	    shengmu.heal(garen,timo);  // 为指定英雄加血
	    shengmu.heal(garen, 300);  // 为指定英雄加hp的血
	    shengmu.heal(timo, 250);   // 为指定英雄加hp的血
		
	}
	
	
	
	
	

}
