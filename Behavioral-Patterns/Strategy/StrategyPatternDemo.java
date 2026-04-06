
class Sorter{
  private SortingStrategy strategy;

  public Sorter(SortingStrategy strategy){
    this.strategy = strategy;
  }
  public void setStrategy(SortingStrategy strategy){
    this.strategy = strategy;
  }
  public void sortArray(int[] numbers){
    System.out.println("\nBefore sorting:");
    for (int num : numbers) {
      System.out.print("   " + num);
    }
    System.out.println("");

    System.out.println("After sorting:");
    strategy.sort(numbers);
    for (int num : numbers) {
      System.out.print("   " + num);
    }
    System.out.println("");
  }
}

interface SortingStrategy {
  void sort(int[] numbers);
}

class BubbleSortStrategy implements SortingStrategy {

  @Override
  public void sort(int[] numbers) {
    System.out.println("   Using bubble sort strategy.");

    int length = numbers.length;
    for (int k = 0; k < length - 1; k++) {
      int cur = 0;
      while (cur < ((length - 1) - k)) {
        if (numbers[cur] > numbers[cur + 1]) {
          int temp = numbers[cur];
          numbers[cur] = numbers[cur + 1];
          numbers[cur + 1] = temp;
        }
        cur++;
      } // end while
    } // end for

  }
}

class InsertionSortStrategy implements SortingStrategy {
  @Override
  public void sort(int[] numbers) {
    System.out.println("   Using insertion sort strategy.");

    int length = numbers.length;
    for (int k = 1; k < length; k++) {
      int temp = numbers[k];
      int cur = k - 1;
      while ((cur > -1) && (temp < numbers[cur])) {
        numbers[cur+1] = numbers[cur];
        cur--;
      }
      numbers[cur+1] = temp;
    }
  }
}

class SelectionSortStrategy implements SortingStrategy{

  @Override
  public void sort(int[] numbers){
    System.out.println("   Using Selection sort strategy.");

    int length = numbers.length;
    int minIdx, min;
    for(int k = 0; k<length-1 ; k++){
      min = numbers[k];
      minIdx=k;
      for(int j = k+1; j<length; j++){
        if(min>numbers[j]){
          min = numbers[j];
          minIdx = j;
        }
      }
      int temp = numbers[k];
      numbers[k] = numbers[minIdx];
      numbers[minIdx] = temp;
    }
  }
}

public class StrategyPatternDemo {
  public static void main(String[] args) {
    BubbleSortStrategy bubbleSort = new BubbleSortStrategy();
    Sorter sorter = new Sorter(bubbleSort);

    int[] data1 = {31,11,71,10,16,91,77,12,55,50};
    sorter.sortArray(data1);

    InsertionSortStrategy insertionSort = new InsertionSortStrategy();
    sorter.setStrategy(insertionSort);
    int[] data2 = {14,1,56,33,90,38,5};
    sorter.sortArray(data2);

    SelectionSortStrategy selectionSort = new SelectionSortStrategy();
    sorter.setStrategy(selectionSort);
    int[] data3 = {77,33,88,11,00,99,22};
    sorter.sortArray(data3);

  }
}