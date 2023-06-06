package co.com.sofka.domainconverter;

import co.com.sofka.model.sofkiano.ExtService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class ExtServiceImpl implements ExtService {
    @Override
    public String fileYML(byte[] ar) {
        try(InputStream inputStream = new ByteArrayInputStream(ar);)
        {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);
            String x = "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
