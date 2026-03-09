public class Instrument {
    // Define instrument properties
    private String name;
    private String type;
    private int volume;
    private String preset;

    // Constructor
    public Instrument(String name, String type, int volume, String preset) {
        this.name = name;
        this.type = type;
        this.volume = volume;
        this.preset = preset;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }
}