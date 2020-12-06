public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(26262));
        //从格林威治时间开始（1970）经过long数值的毫秒数到达的时间点
        //无参表示当前的时间点
        java.util.Date d = new java.util.Date();//1.无参2.long类型
        java.sql.Date d1 = new java.sql.Date(9999);
        //数据库需要带时间时使用
        java.sql.Timestamp t = new java.sql.Timestamp(656);//long类型
    }
}
