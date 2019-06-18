package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.CoordinationApplication;
import com.innowise.coordination.entity.AbstractEntity;
import com.innowise.coordination.repository.AbstractRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoordinationApplication.class)
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.default_schema=coordination_schema_test",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public abstract class AbstractRepositoryTests<E extends AbstractEntity, R extends AbstractRepository<E, L>, L>
        extends CommonRepositoryTests<E, R, L> {

    @Autowired
    private R repository;

    private E entity;

    private L id;

    abstract E getEntity();

    abstract L getEntityId();

    @Before
    public void setup(){
        entity = getEntity();
        id = getEntityId();
    }

    @Test
    public void testFindAll(){
        Assert.assertEquals(1, findAll().size());
    }

    @Test
    public void testFindById(){
        Assert.assertTrue(repository.existsById(id));
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
        delete(id);
        Assert.assertFalse(repository.existsById(id));
    }

    @Test
    public void testDeleteAll(){
        deleteAll();
        Assert.assertTrue(findAll().isEmpty());
    }

}
