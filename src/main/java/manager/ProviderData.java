package manager;

import lombok.Builder;
import lombok.Getter;
import models.User;
import models.UserLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProviderData {

    @DataProvider
    public Iterator<Object[]> userDto() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withEmail("nefr42@gmail.com")
                .withPassword("Rita12345$")

        });
        list.add(new Object[]{new User()
                .withEmail("nefr597@gmail.com")
                .withPassword("Rita12300$")

        });

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDtoRegPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("nefr" + i + "@gmail.com")
                .withPassword("Rita12300$")

        });
        list.add(new Object[]{new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("rott" + i + "@gmail.com")
                .withPassword("Rita12300$")
        });


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDtoRegNegative() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("nefr" + i + "@gmail.com")
                .withPassword("Rita12300фыб")
        });
        list.add(new Object[]{new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("rott" + i + "@gmail.com")
                .withPassword(" ")
        });
        list.add(new Object[]{new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("rott" + i + "@gmail.com")
                .withPassword("123445")
        });


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userNegativDtoCSV()  {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("src/test/resources/reg_negativ_dataset.csv")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User()
                    .withName(split[0])
                    .withLastName(split[1])
                    .withEmail(split[2])
                    .withPassword(split[3])
            });
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list.iterator();
    }


}
