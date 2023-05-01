package model;

public class Resource extends Asset{
    private ResourceEnum type;

    public Resource(ResourceEnum type, int amount) {
        super(amount);
        this.type = type;
    }

    public ResourceEnum getType() {
        return type;
    }

    @Override
    public void addAsset(int amount) {
        super.addAsset(amount);
    }
}
