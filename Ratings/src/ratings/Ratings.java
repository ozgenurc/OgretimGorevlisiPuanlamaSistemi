package ratings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ozgen
 */
public class Ratings {

    public static void main(String[] args) {
        Scanner dosya = null;
        try {
            dosya = new Scanner(new FileInputStream("C:\\Users\\ozgen\\Documents\\NetBeansProjects\\Ratings\\src\\ratings\\kurum.txt"));
            System.out.println("Dosyadan alinan müsteriler ile ilgili bilgiler yazdiriliyor...");
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadi.");
            System.exit(0);
        }

        int advisorCount = 0;
        int count = -1; //student sayisini bulmak icin kullanacagiz
        int usCount = 0; //undergraduate student sayacı
        int gsCount = 0; //graduate student sayacı
        int aiCount = 0; //yapay zekaya yönelenlerin sayacı

        Student[] students = new Student[200];
        int ratings[][] = new int[200][];

        //Dosyadan okuma işlemleri
        while (dosya.hasNextLine()) {
            StringTokenizer line = new StringTokenizer(dosya.nextLine(), (","));
            String currentWord = line.nextToken();
            if (count == -1 & (!currentWord.equals("u") || !currentWord.equals("g"))) {
                advisorCount = line.countTokens(); // ilk satır advisor sayısını verir
                for (int i = 0; i < 200; i++) {
                    ratings[i] = new int[advisorCount];
                }
                count++;
                continue;
            }
            if (currentWord.equals("u") || currentWord.equals("g")) {
                int studentID = Integer.parseInt(line.nextToken(","));
                String name = line.nextToken(",");
                String surname = line.nextToken(",");

                if (currentWord.equals("u")) {
                    String track = line.nextToken(",");
                    if (track.equals("Yapay zeka")) {
                        aiCount++;
                    }
                    UnderGraduate student = new UnderGraduate(studentID, name, surname, track);
                    students[count] = student;
                    usCount++;
                } else {
                    String subject = line.nextToken(",");
                    String advisor = line.nextToken(",");
                    Graduate student = new Graduate(studentID, name, surname, subject, advisor);
                    students[count] = student;
                    gsCount++;
                }
                StringTokenizer ratingsLine = new StringTokenizer(dosya.nextLine());
                for (int i = 0; i < advisorCount; i++) {
                    ratings[count][i] = Integer.parseInt(ratingsLine.nextToken(","));
                }
            }
            count++;
        }

        //madde 1:
        System.out.println("Her bir öğretim elemanı için ortalama derecelendirme puanları:");
        float avgRating[] = new float[advisorCount];
        for (int i = 0; i < advisorCount; i++) {
            float total = 0;
            for (int j = 0; j < count; j++) {
                total += ratings[j][i];
            }
            float avg = total / count;
            avgRating[i] = avg;
            System.out.print(i + 1 + ". öğretim elemanının ortalama puanı: ");
            System.out.printf("%.1f%n", avg);
        }
        System.out.println();

        //madde 2: 
        System.out.println("Her bir öğretim elemanı için lisans öğrencileri tarafından verilen ortalama derecelendirme puanları:");
        for (int i = 0; i < advisorCount; i++) {
            float totalUs = 0;
            for (int j = 0; j < usCount + gsCount; j++) {
                Student student = students[j];
                if (student instanceof UnderGraduate) {
                    totalUs += ratings[j][i];
                }
            }
            float avgUs = totalUs / usCount;
            System.out.print(i + 1 + ". öğretim elemanının ortalama puanı: ");
            System.out.printf("%.1f%n", avgUs);
        }
        System.out.println();

        //madde 3: 
        System.out.println("Her bir öğretim elemanı için lisansüstü öğrencileri tarafından verilen ortalama derecelendirme puanları:");
        for (int i = 0; i < advisorCount; i++) {
            float totalGs = 0;
            for (int j = 0; j < usCount + gsCount; j++) {
                Student student = students[j];
                if (student instanceof Graduate) {
                    totalGs += ratings[j][i];
                }
            }
            float avgGs = totalGs / gsCount;
            System.out.print(i + 1 + ". öğretim elemanının ortalama puanı: ");
            System.out.printf("%.1f%n", avgGs);
        }
        System.out.println();

        //madde 4:
        System.out.println("Her bir öğretim elemanı için sadece lisans öğrencilerinden izlediği patika yapay zeka olanlar tarafından verilen ortalama derecelendirme puanları:");
        for (int i = 0; i < advisorCount; i++) {
            float totalAi = 0;
            for (int j = 0; j < usCount + gsCount; j++) {
                Student student = students[j];
                if (student instanceof UnderGraduate) {
                    if ((((UnderGraduate) student).track).equals("Yapay zeka")) {
                        totalAi += ratings[j][i];
                    }
                }
            }
            float avgAi = totalAi / aiCount;
            System.out.print(i + 1 + ". öğretim elemanının ortalama puanı: ");
            System.out.printf("%.1f%n", avgAi);
        }
        System.out.println();

        //madde 5:
        System.out.println("Her bir öğretim elemanı için, o öğretim elemanına ait ortalama derecelendirmenin altında puan vermiş lisans öğrencilerinin bilgileri:");
        for (int i = 0; i < advisorCount; i++) {
            System.out.println(i + 1 + ". öğretim elemanı için ortalama altı puan veren lisans öğrencileri:");
            for (int j = 0; j < count; j++) {
                if (students[j] instanceof UnderGraduate && ratings[j][i] < avgRating[i]) {
                    System.out.println(students[j]);
                }
            }
        }
        System.out.println();

        //madde 6:
        System.out.println("Her bir öğretim elemanı için, o öğretim elemanına ait ortalama derecelendirmenin altında puan vermiş lisansüstü öğrencilerin bilgileri:");
        for (int i = 0; i < advisorCount; i++) {
            System.out.println(i + 1 + ". öğretim elemanı için ortalama altı puan veren lisansüstü öğrenciler:");
            for (int j = 0; j < count; j++) {
                if (students[j] instanceof Graduate && ratings[j][i] < avgRating[i]) {
                    System.out.println(students[j]);
                }
            }
        }
        System.out.println();

        //madde 7:
        int[] similarity = new int[199]; // Benzer öğrenciler için liste
        int scannerCount = 0; //Klavyeden alınan öğrenci sayısını belirlemek için sayaç
        Scanner input = new Scanner(System.in);
        String studentType, studentName, studentSurname, studentTrack, studentSubject, studentAdvisor;
        int studentId;

        for (int i = 0; i < 200 - count; i++) {
            System.out.println("Öğrenci tipi lisans öğrecileri için u, lisansüstü öğrenciler için g, çıkmak için -1:");
            studentType = input.nextLine();
            if ("u".equals(studentType)) {
                System.out.println("Öğrencinin numarası: ");
                studentId = input.nextInt();
                input.nextLine();

                System.out.println("Öğrencinin adı: ");
                studentName = input.nextLine();

                System.out.println("Öğrencinin soyadı: ");
                studentSurname = input.nextLine();

                System.out.println("Öğrencinin alanı: ");
                studentTrack = input.nextLine();

                UnderGraduate student = new UnderGraduate(studentId, studentName, studentSurname, studentTrack);
                students[count + i] = student;
            } else {
                if (studentType.equals("g")) {
                    System.out.println("Öğrencinin numarası: ");
                    studentId = input.nextInt();
                    input.nextLine();

                    System.out.println("Öğrencinin adı: ");
                    studentName = input.nextLine();

                    System.out.println("Öğrencinin soyadı: ");
                    studentSurname = input.nextLine();

                    System.out.println("Öğrencinin tez konusu: ");
                    studentSubject = input.nextLine();

                    System.out.println("Öğrencinin danışmanı: ");
                    studentAdvisor = input.nextLine();

                    Graduate student = new Graduate(studentId, studentName, studentSurname, studentSubject, studentAdvisor);
                    students[count + i] = student;
                } else {
                    break;
                }
            }
            for (int j = 0; j < advisorCount - 1; j++) {
                System.out.println(j + 1 + ". öğretim elemanının puanı: ");
                int point = input.nextInt();
                if (point < 0 || point > 5) {
                    break;
                }
                ratings[count + i][j] = point;
            }

            System.out.println();
            for (int j = 0; j < gsCount + usCount; j++) { // benzerlik oranlarını bulan döngü
                int similarityRatio = 0;
                for (int k = 0; k < advisorCount - 1; k++) {
                    similarityRatio += Math.abs(ratings[count + i][k] - ratings[j][k]);
                }
                similarity[j] = similarityRatio;
            }
            float minToplam = 0, minCount = 0;
            int similarityStuIndex = minimumIndex(similarity);
            for (int j = 0; j < similarity.length; j++) {
                if (similarity[j] == 0) {
                    break;
                }
                if (similarity[similarityStuIndex] == similarity[j]) {
                    minToplam += ratings[j][advisorCount - 1];
                    minCount++;
                }
            }
            float guess = minToplam / minCount;
            ratings[count + i][advisorCount - 1] = (int) guess; //Son öğretim görevlisinin puanının listeye eklenmesi
            input.nextLine();
            scannerCount++;
        }
        System.out.println();

        //madde 8:
        System.out.println("Klavyeden alınan öğrenciler ile ilgili bilgiler yazdırılıyor...\n");
        for (int i = count; i < 200 - count; i++) { // klavyeden alınabilecek öğrenci sayısı kadar döner
            if (students[i] == null) { // Alınan öğrenci sayısı 200'den küçükse listede null olacağı için yapıldı
                System.out.println();
                break;
            }
            System.out.println(students[i]);
            System.out.println ("Öğretim elemanları için öğrencinin verdiği puanlar sırasıyla: ");
            for (int j = 0; j < advisorCount; j++) {
                System.out.print(ratings[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < advisorCount; i++) { // Klavyeden alınan öğrencilerin verdiği ortalama puanları hesaplayan döngü
            float total = 0;
            for (int j = count; j < count + scannerCount; j++) {
                total += ratings[j][i];
            }
            float avg = total / scannerCount;
            System.out.print(i + 1 + ". öğretim elemanının ortalama puanı: ");
            System.out.printf("%.1f%n", avg);
        }
        dosya.close();
    }

    public static int minimumIndex(int[] dizi) { // min benzerlik oranının indiexini bulmak için metot
        int index = 0;
        int min = dizi[index];
        for (int i = 1; i < dizi.length; i++) {
            if (dizi[i] == 0) {
                continue;
            }
            if (dizi[i] < min) {
                min = dizi[i];
                index = i;
            }
        }
        return index;
    }
}
