public class Main {
    public static void main(String[] args) {
//        AL<Integer> is = new AL<>(1,2,3,4,5,6);
//        is.reverse();
//        for (int i = 0; i < is.size(); i++) {
//            System.out.print(is.get(i)+" ");
//        }
        System.out.println(new AL<Integer>(1,2,3,4,5).endsWith(new AL<Integer>(3,4,5)));
    }
}
