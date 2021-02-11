public class WaterBoy {

  public int startWaterVolume;
  public int waterReceive;
  public int noWaterReceive;
  public int waterGive;
  public int noWaterGive;
  public int receiveCount;
  public int giveCount;
  public int failedReceiveCount;
  public int failedGiveCount;
  public Waterpot waterpot;
  private float tempPerc = 0.0f;

  public WaterBoy(int volume, int waterVolume) {
    waterpot = new Waterpot(volume, waterVolume);
    startWaterVolume = volume;
  }

  public void topUp(int water){
    if(isEnoughVolumeForTopUp(water)){
      waterpot.fill(water);
      waterReceive +=water;
      receiveCount++;
    } else{
      noWaterReceive += water;
      failedReceiveCount++;
    }
  }

  public void scoop(int water){
    if(isEnoughVolumeForScoop(water)){
      waterpot.pickUp(water);
      waterGive += water;
      giveCount++;
    } else{
      noWaterGive += water;
      failedGiveCount++;
    }
  }

  private boolean isEnoughVolumeForTopUp(int water){
    return waterpot.freeVolume()>water;
  }

  private boolean isEnoughVolumeForScoop(int water){
    return waterpot.waterVolume > water;
  }

  private double receiveErrorPercent(){
    tempPerc =  (float) failedReceiveCount/ (float) receiveCount*100;
    return tempPerc;
  }

  private double giveErrorPercent(){
    tempPerc = (float) failedGiveCount/ (float) giveCount *100;
    return tempPerc;
  }

  public void pringReport(){
    System.out.println("Попыток налить воду в бочку: " + receiveCount);
    System.out.printf("Процент ошибок при наполнении: %.2f%%%n", receiveErrorPercent());
    System.out.println("Наполненный объем воды за указанный период: " + waterReceive);
    System.out.println("Ненаполненный объем воды за указанный период: " + noWaterReceive);

    System.out.println("Попыток зачерпнуть воду из бочки: " + giveCount);
    System.out.printf("Процент ошибок при зачерпывании: %.2f%%%n", giveErrorPercent());
    System.out.println("Вычерпаный объем воды за указанный период: " + waterGive);
    System.out.println("Невычерпаный объем воды за указанный период: " + noWaterGive);
  }
}