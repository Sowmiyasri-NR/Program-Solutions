public class BuilderPatternExample {

    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .setGraphicsCard("NVIDIA RTX 4080")
            .build();

        Computer officeComputer = new Computer.Builder()
            .setCPU("Intel i5")
            .setRAM("16GB")
            .setStorage("512GB SSD")
            .build();

        gamingComputer.showConfig();
        officeComputer.showConfig();
    }
}

class Computer {
    private String CPU;
    private String RAM;
    private String Storage;
    private String GraphicsCard;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GraphicsCard = builder.GraphicsCard;
    }

    public void showConfig() {
        System.out.println("CPU: " + CPU);
        System.out.println("RAM: " + RAM);
        System.out.println("Storage: " + Storage);
        System.out.println("Graphics Card: " + (GraphicsCard != null ? GraphicsCard : "None"));
        System.out.println();
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String Storage;
        private String GraphicsCard;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String Storage) {
            this.Storage = Storage;
            return this;
        }

        public Builder setGraphicsCard(String GraphicsCard) {
            this.GraphicsCard = GraphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
