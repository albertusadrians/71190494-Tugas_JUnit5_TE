public class Pajak {
    public double getPajak(double salary){
        if (salary >= 0 && salary <= 4000000) {
            return 0;
        } else if (salary > 4000000 && salary <= 15000000) {
            return 10;
        } else if (salary > 15000000  && salary <= 40000000) {
            return 22;
        } else if (salary > 40000000 && salary <= 999999999999f) {
            return 40f;
        }
        return -1;
    }
}
