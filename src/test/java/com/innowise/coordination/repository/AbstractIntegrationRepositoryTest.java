package com.innowise.coordination.repository;

import com.innowise.coordination.CoordinationApplication;
import com.innowise.coordination.entity.AbstractEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoordinationApplication.class)
@ActiveProfiles("test")
public abstract class AbstractIntegrationRepositoryTest<E extends AbstractEntity<L>, R extends AbstractRepository<E, L>, L>
        extends CommonIntegrationRepositoryTest<E, R, L> {

    @Autowired
    private R repository;

    private E entity;

    protected abstract E getEntity();

    @Before
    public void setup(){
        entity = getEntity();
        entity = repository.save(entity);
    }

    @Test
    public void testFindAll(){
        Assert.assertEquals(1, findAll().size());
    }

    @Test
    public void testFindById(){
        Assert.assertTrue(repository.existsById(entity.getId()));
    }

    @Test
    public void testSave(){
        Assert.assertEquals(entity, save(entity));
    }

    @Test
    public void testUpdate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        entity.setUpdated(LocalDateTime.now());
        entity = save(entity);
        Assert.assertEquals(LocalDateTime.now().format(dateTimeFormatter), entity.getUpdated().format(dateTimeFormatter));
    }

    @Test
    public void testDeleteById(){
        delete(entity.getId());
        Assert.assertFalse(repository.existsById(entity.getId()));
    }

    @Test
    public void testDeleteAll(){
        deleteAll();
        Assert.assertTrue(findAll().isEmpty());
    }

}
