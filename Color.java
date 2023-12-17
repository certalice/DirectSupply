/**
 * I used a previous color enum I had creating for a class assignment to introduce some colors
 */
enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m");
    private final String ansi;
    Color(String ansi) {
        this.ansi = ansi;
    }
    @Override
    public String toString() {
        return ansi;
    }
}
