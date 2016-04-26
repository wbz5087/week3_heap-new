import java.lang.reflect.Array;
import static java.lang.Math.floor;
/**
 * Created by wubingzhang on 4/23/16.
 */
public class Heap<T extends Comparable> {

    public int length;
    public T[] heap;
    static int p = 0;
    public int heapsize;

    Heap(T[] b, int n) {
        heap = b;
        heapsize = n;
        length = n;
    }

    public void setHeap(T[] b, int n) {
        heap = b;
        heapsize = n;
        length = n;
    }

    public int left(int i) {
        return i * 2;
    }

    public int right(int i) {
        return i * 2 + 1;
    }

    public int compMax(int i1, int i2) {
        return heap[i1 - 1].compareTo(heap[i2 - 1]);
    }

    public int compMin(int i1, int i2) {
        return heap[i2 - 1].compareTo(heap[i1 - 1]);
    }

    public void build_max_heap() {
        for (int i = (int) floor(heapsize / 2); i > 0; i--) {
            max_heapify(i);
        }
    }

    public void build_min_heap() {
        for (int i = (int) floor(heapsize / 2); i > 0; i--) {
            min_heapify(i);
        }
    }

    private void swap(int i1, int i2) {
        T temp = heap[i1 - 1];
        heap[i1 - 1] = heap[i2 - 1];
        heap[i2 - 1] = temp;
    }

    public void max_heapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest = 0;
        if (l <= heapsize && compMax(l, i) > 0) {
            largest = l;
        } else {
            largest = i;
        }
        if (r <= heapsize && compMax(r, largest) > 0) {
            largest = r;
        }
        if (largest != i) {
            swap(i, largest);
            max_heapify(largest);
        }
    }

    public void min_heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = 0;
        if (l <= heapsize && compMin(l, i) > 0) {
            smallest = l;
        } else {
            smallest = i;
        }
        if (r <= heapsize && compMin(r, smallest) > 0) {
            smallest = r;
        }
        if (smallest != i) {
            swap(i, smallest);
            min_heapify(smallest);
        }
    }

    public boolean insertMax(T n) {
        //System.out.println("heapsize: " + heapsize + " " + "length: " + length);
        if (heapsize == length) {
            return false;
        }
        heap[heapsize++] = n;
        build_max_heap();
        return true;
    }

    public boolean insertMin(T n) {
        if (heapsize == length) {
            return false;
        }
        heap[heapsize++] = n;
        build_min_heap();
        return true;
    }

    public T extract_max() {
        T temp = heap[0];
        if (heapsize > 0) {
            heapsize--;
            heap[0] = heap[heapsize];
            heap[heapsize] = null;
            max_heapify(1);
        } else {
//            if (length > 0) {
//                length--;
//            }
            //   System.out.println("Queue is empty.");
        }
        // System.out.println("heapsize: " + heapsize + " " + "length: " + length);
        return temp;
    }

    public T extract_min() {
        T temp = heap[0];
        if (heapsize-- > 0) {
            heap[0] = heap[heapsize];
            heap[heapsize] = null;
            min_heapify(1);
        } else {
            //    length--;
            //   System.out.println("Queue is empty.");
        }
        return temp;
    }

    public T getHeap() {
        if (p == heapsize + 1) {
            p = 0;
        }
        return heap[p++];
    }

    public T[] maxHeapsort() {
        //Heap <T> temp = new Heap(heap, heapsize);
        Class<T[]> S = (Class<T[]>) heap.getClass();
        T[] x;
        x = (T[]) Array.newInstance(S.getComponentType(), length);
        for (int i = 0; i < length; i++) {
            x[i] = extract_max();
        }
        return x;
    }

    public T[] minHeapsort() {

        Class<T[]> S = (Class<T[]>) heap.getClass();
        T[] x;
        x = (T[]) Array.newInstance(S.getComponentType(), length);
        for (int i = 0; i < length; i++) {
            x[i] = extract_min();
        }
        return x;
    }

    public String toString() {
        String str = "";
        for (T heap1 : heap) {
            str += heap1 + " ";
        }
        return str;
    }
}
