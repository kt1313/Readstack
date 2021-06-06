package pl.javastart.readstack.domain.api;

import pl.javastart.readstack.domain.discovery.Discovery;
import pl.javastart.readstack.domain.discovery.DiscoveryDao;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {

    //reszta bez zmian

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream().map(DiscoveryMapper::map)
                .collect(Collectors.toList());
    }

}