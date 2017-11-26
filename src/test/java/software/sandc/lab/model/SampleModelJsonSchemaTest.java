package software.sandc.lab.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import software.sandc.lab.view.AdminView;
import software.sandc.lab.view.UserView;

public class SampleModelJsonSchemaTest {


    @Test
    public void shouldCreateJsonSchemaForSampleModelUsingUserView() throws JsonProcessingException {        
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(UserView.class));
        JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);

        // when
        JsonSchema jsonSchema = generator.generateSchema(SampleModel.class);
        
        //then
        assertEquals(jsonSchema.asObjectSchema().getProperties().keySet().toString(), "[username]");
    }

    @Test
    public void shouldCreateJsonSchemaForSampleModelUsingUserViewIncludingDefaultView() throws JsonProcessingException {        
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(UserView.class));
        JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);
        
        // when
        JsonSchema jsonSchema = generator.generateSchema(SampleModel.class);
        
        //then
        assertEquals(jsonSchema.asObjectSchema().getProperties().keySet().toString(), "[username, version]");
    }
    
    @Test
    public void shouldCreateJsonSchemaForSampleModelUsingAdminView() throws JsonProcessingException {        
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(AdminView.class));
        JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);

        // when
        JsonSchema jsonSchema = generator.generateSchema(SampleModel.class);
        
        //then
        assertEquals(jsonSchema.asObjectSchema().getProperties().keySet().toString(), "[id]");
    }
    
    @Test
    public void shouldCreateJsonSchemaAsStringForSampleModelUsingUserView() throws JsonProcessingException {        
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(AdminView.class));
        JsonSchemaGenerator generator = new JsonSchemaGenerator(objectMapper);

        // when
        JsonSchema jsonSchema = generator.generateSchema(SampleModel.class);
        ObjectMapper schemaObjectMapper = new ObjectMapper();
        String jsonSchemaAsString = schemaObjectMapper.writeValueAsString(jsonSchema);
        
        //then
        assertEquals(jsonSchemaAsString,"{\"type\":\"object\",\"id\":\"urn:jsonschema:software:sandc:lab:model:SampleModel\",\"properties\":{\"id\":{\"type\":\"string\"}}}");
    }
}
