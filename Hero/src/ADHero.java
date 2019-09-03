
class Hero1 {     
    String name; //姓名     
    float hp; //血量    
    float armor; //护甲     
    int moveSpeed; //移动速度
}


public class ADHero extends Hero1 {
	 
    public void attack() {
        System.out.println(name + " 进行了一次攻击 ，但是不确定打中谁了");
    }
 
    // 可变数量的参数
    // 在方法里，使用操作数组的方式处理参数heros即可
    public void attack(Hero1... heros) {
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);
 
        }
    }
 
    public static void main(String[] args) {
        ADHero bh = new ADHero();
        bh.name = "赏金猎人";
 
        Hero1 h1 = new Hero1();
        h1.name = "盖伦";
        Hero1 h2 = new Hero1();
        h2.name = "提莫";
 
        bh.attack(h1);
        bh.attack(h1, h2);
 
    }
 
}