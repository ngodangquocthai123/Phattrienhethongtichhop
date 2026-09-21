class Animal {
    String name;
    double weight;

    public Animal(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
}

class Lion extends Animal {
    double eat;

    public Lion(String name, double weight, double eat) {
        super(name, weight);
        this.eat = eat;
    }

    public void display() {
        System.out.println("Su tu: " + name);
        System.out.println("Can nang: " + weight + " can");
        System.out.println("An moi ngay: " + eat + " can thit");
    }
}

class Snake extends Animal {
    double length;

    public Snake(String name, double weight, double length) {
        super(name, weight);
        this.length = length;
    }

    public void display() {
        System.out.println("Con ran: " + name);
        System.out.println("Can nang: " + weight + " can");
        System.out.println("Chieu dai: " + length + " met");
    }
}

class Monkey extends Animal {
    String favoriteFood;

    public Monkey(String name, double weight, String favoriteFood) {
        super(name, weight);
        this.favoriteFood = favoriteFood;
    }

    public void display() {
        System.out.println("Con khi: " + name);
        System.out.println("Can nang: " + weight + " can");
        System.out.println("Thuc an yeu thich: " + favoriteFood);
    }
}

public class bai6 {
    public static void main(String[] args) {

        Lion lion = new Lion("Leo", 300, 5);
        Snake snake = new Snake("Boa", 50, 5);
        Monkey monkey = new Monkey("George", 150, "Chuoi");

        lion.display();

        System.out.println();

        snake.display();

        System.out.println();

        monkey.display();
    }
}