package org.apache.commons.lang3.builder;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectionToStringBuilderExcludeNullValuesTest {

    class TestFixture{
        private Integer testIntegerField;
        private String testStringField;
        
        public TestFixture(Integer a, String b){
            this.testIntegerField = a;
            this.testStringField = b;
        }
    }
    
    private static final String INTEGER_FIELD_NAME = "testIntegerField";
    private static final String STRING_FIELD_NAME = "testStringField";
    private final TestFixture BOTH_NON_NULL = new TestFixture(0, "str");
    private final TestFixture FIRST_NULL = new TestFixture(null, "str");
    private final TestFixture SECOND_NULL = new TestFixture(0, null);
    private final TestFixture BOTH_NULL = new TestFixture(null, null);
    
    @Test
    public void test_NonExclude(){
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));

        //make the both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }
    
    @Test
    public void test_excludeNull(){
        
        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, true, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertFalse(toString.contains(STRING_FIELD_NAME));
        
        //both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, true, null);
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }
    
    @Test
    public void test_ConstructorOption(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        assertTrue(builder.isExcludeNullValues());
        
        String toString = builder.toString();
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
        assertTrue(toString.contains(STRING_FIELD_NAME));
        
        builder = new ReflectionToStringBuilder(SECOND_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
        assertFalse(toString.contains(STRING_FIELD_NAME));
        
        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

}
