package network.udp.client;

public class UpdatedState
{
    private String jsonProperty;
    private double posX;
    private double posY;
    private double bulletSpeedComponentX;
    private double bulletSpeedComponentY;


    public UpdatedState(String jsonProperty, double x, double y)
    {
        this.jsonProperty = jsonProperty;
        this.posX = x;
        this.posY = y;
    }

    public UpdatedState(String jsonProperty, double x, double y, double dx, double dy)
    {
        this.jsonProperty = jsonProperty;
        this.posX = x;
        this.posY = y;
        this.bulletSpeedComponentX = dx;
        this.bulletSpeedComponentY = dy;
    }

    // Getters and setters
    public String getJsonProperty()
    {
        return jsonProperty;
    }

    public void setJsonProperty(String jsonProperty)
    {
        this.jsonProperty = jsonProperty;
    }

    public double getPosX()
    {
        return posX;
    }

    public double getPosY()
    {
        return posY;
    }

    public double getBulletSpeedComponentX()
    {
        return bulletSpeedComponentX;
    }

    public double getBulletSpeedComponentY()
    {
        return bulletSpeedComponentY;
    }

    public void setPosX(double posX)
    {
        this.posX = posX;
    }

    public void setPosY(double posY)
    {
        this.posY = posY;
    }

    public void setBulletSpeedComponentX(double bulletSpeedComponentX)
    {
        this.bulletSpeedComponentX = bulletSpeedComponentX;
    }

    public void setBulletSpeedComponentY(double bulletSpeedComponentY)
    {
        this.bulletSpeedComponentY = bulletSpeedComponentY;
    }
}
