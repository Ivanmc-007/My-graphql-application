package com.ivan.mygraphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.ivan.mygraphql.model.entity.Owner;
import com.ivan.mygraphql.model.entity.Vehicle;
import com.ivan.mygraphql.service.OwnerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerResolverIntegrationTest {

    private final static Long TEST_OWNER_ID = 1L;
    private final static Long TEST_OWNER_ID_NEW = 2L;
    private final static String TEST_OWNER_LOGIN = "John";
    private final static String TEST_OWNER_EMAIL = "john-john@gmail.com";

    private final static Long TEST_VEHICLE_ID = 21L;
    private final static String TEST_VEHICLE_TYPE = "bike";

    private final static Owner TEST_OWNER = Owner.builder()
            .id(TEST_OWNER_ID)
            .login(TEST_OWNER_LOGIN)
            .email(TEST_OWNER_EMAIL)
            .vehicles(new HashSet<>()).build();

    private final static Set<Vehicle> TEST_VEHICLES = Collections.singleton(Vehicle.builder()
            .id(TEST_VEHICLE_ID)
            .type(TEST_VEHICLE_TYPE)
            .modelCode("XXX-model")
            .brandName("Some brand")
            .formattedDate("2020-08-08")
            .build());

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private OwnerResolver ownerResolver;

    @Before
    public void metBefore() {
        when(ownerService.getOwnerById(any(Long.class))).then(invocationOnMock -> {
            Long id = (Long) invocationOnMock.getArguments()[0];
            return TEST_OWNER_ID.equals(id) ? Optional.of(TEST_OWNER) : Optional.empty();
        });

        when(ownerResolver.getVehicles(any(Owner.class))).then(invocationOnMock ->
            new ArrayList<>(TEST_VEHICLES));

        when(ownerService.save(any(Owner.class))).then(invocationOnMock -> {
            Owner owner = (Owner) invocationOnMock.getArguments()[0];
            owner.setId(TEST_OWNER_ID_NEW);
            return owner;
        });
    }

    @Test
    public void toCheckContext() {
        assertThat(graphQLTestTemplate).isNotNull();
        assertThat(ownerService).isNotNull();
        assertThat(ownerResolver).isNotNull();
    }

    @Test
    public void whenGetOwnerById_ThenReturnOwner() throws IOException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("queries/owner-get-by-id.graphql");
        assertThat(response).isNotNull();
        assertThat(String.valueOf(TEST_OWNER_ID)).isEqualTo(response.get("$.data.getOwnerById.id"));
        assertThat(TEST_OWNER_LOGIN).isEqualTo(response.get("$.data.getOwnerById.login"));
        assertThat(TEST_OWNER_EMAIL).isEqualTo(response.get("$.data.getOwnerById.email"));
        assertThat(String.valueOf(TEST_VEHICLE_ID)).isEqualTo(response.get("$.data.getOwnerById.vehicles[0].id"));
        assertThat(TEST_VEHICLE_TYPE).isEqualTo(response.get("$.data.getOwnerById.vehicles[0].type"));
    }

    @Test
    public void whenSaveOwner_ThenReturnNewId() throws IOException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("queries/owner-create.graphql");
        assertThat(response).isNotNull();
        assertThat(String.valueOf(TEST_OWNER_ID_NEW)).isEqualTo(response.get("$.data.createOwner.id"));
    }


}
