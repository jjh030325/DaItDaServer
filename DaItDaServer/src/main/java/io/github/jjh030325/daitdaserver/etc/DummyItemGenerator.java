package io.github.jjh030325.daitdaserver.etc;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DummyItemGenerator {
    static final String[] baseNames = {
            "초코파이", "라면", "책상", "노트북", "세탁기", "화장품", "후드티", "영어책", "사료", "피규어"
    };

    static final String[] adjectives = {
            "신제품", "인기", "한정판", "저렴한", "프리미엄", "중고", "국산", "고급", "고성능", "리퍼"
    };

    static final String[] types = {
            "FOOD", "CLOTHES", "ELECTRONICS", "BOOK", "HOUSEWARE", "TOY", "BEAUTY", "FURNITURE", "PET", "ETC"
    };

    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        FileWriter writer = new FileWriter("dummy_items.sql");

        for (int i = 1; i <= 500_0000; i++) {
            String name = adjectives[rand.nextInt(adjectives.length)] + " " + baseNames[rand.nextInt(baseNames.length)] + " " + i;
            long price = 1000 + rand.nextInt(99000); // 1,000 ~ 100,000
            String type = types[rand.nextInt(types.length)];
            long sellerId = 1 + rand.nextInt(500); // 500명 가정

            String sql = String.format(
                    "INSERT INTO item_table (seller_id, name, price, type, created_at, updated_at, etc) " +
                            "VALUES (%d, '%s', %d, '%s', NOW(), NOW(), '자동 생성된 상품입니다.');\n",
                    sellerId, name.replace("'", "''"), price, type
            );
            writer.write(sql);

            if (i % 10000 == 0) {
                System.out.printf("✔ %d 개 생성됨...\n", i);
            }
        }

        writer.close();
        System.out.println("SQL 파일 생성 완료: dummy_items.sql");
    }
}
