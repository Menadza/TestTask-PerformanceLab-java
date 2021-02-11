public class Waterpot {

  int totalVolume;
  int waterVolume;

  public Waterpot(int totalVolume, int waterVolume) {
    this.totalVolume = totalVolume;
    this.waterVolume = waterVolume;
  }

  public int freeVolume() {
    return totalVolume - waterVolume;
  }

  public void fill(int water) {
    waterVolume += water;
  }

  public void pickUp(int water) {
    waterVolume -= water;
  }

}

