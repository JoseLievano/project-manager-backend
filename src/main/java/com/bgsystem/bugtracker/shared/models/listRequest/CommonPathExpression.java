package com.bgsystem.bugtracker.shared.models.listRequest;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public abstract class CommonPathExpression <Entity> {

    protected ArrayList<FilterRequest> filters;

    protected PathBuilder<Entity> entityPath;

    protected ArrayList<String> entityFields = new ArrayList<>();

    public CommonPathExpression(){}

    public void setFilters(ArrayList<FilterRequest> filters){
        this.filters = filters;
    }

    public BooleanExpression getExpression() throws BadOperator {

        BooleanExpression expressions = Expressions.asBoolean(true).isTrue();

        for (FilterRequest filter : filters){

            expressions = expressions.and(this.entityExpressionsSeparator(filter));

        }

        return expressions;

    }

    //Check if the field is a reference to another entity
    protected BooleanExpression entityExpressionsSeparator(FilterRequest filter) throws BadOperator {

        String field = filter.getField();

        if (entityFields.contains(field)){
            return this.getCustomPathExpression(filter);
        }else{
            return this.commonExpressionsSeparator(filter);
        }

    }

    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return null;
    }


    protected BooleanExpression commonExpressionsSeparator(FilterRequest filter) throws BadOperator {

        BooleanExpression expression = null;

        String field = filter.getField();
        Class<?> entityClass = entityPath.getType();
        Class<?> fieldType = null;
        try {
            Field entityField = entityClass.getDeclaredField(filter.getField());
            fieldType = entityField.getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Type: " + fieldType);

        for (FilterOperator operator : filter.getOperations()){

            if (Number.class.isAssignableFrom(fieldType)){

                expression = this.addOrExpression(expression, this.getNumberExpression(field, operator, fieldType));

            }else if (isADate(operator.getValue())){

                expression = this.addOrExpression(expression, this.getDateExpression(field, operator));

            }else if (isABoolean(operator.getValue())) {

                expression = this.addOrExpression(expression, this.getBooleanExpression(field, operator));

            }else{

                System.out.println("Not anything");

                expression = this.addOrExpression(expression, this.getStringExpression(field, operator));

            }

        }

        return expression;
    }

    protected BooleanExpression addOrExpression(BooleanExpression expression, BooleanExpression newExpression){
        if (expression == null){
            expression = newExpression;
        }else{
            expression = expression.or(newExpression);
        }
        return expression;
    }

    protected BooleanExpression getStringExpression(String field, FilterOperator operator) throws BadOperator {

        StringPath path = entityPath.getString(field);

        return getStringPathBooleanExpression(path, operator);

    }

    protected BooleanExpression getStringPathBooleanExpression(StringPath path, FilterOperator operator) throws BadOperator {

        String value = operator.getValue();

        return switch (operator.getOperator()) {
            case ":" -> path.containsIgnoreCase(value);
            case "=" -> path.equalsIgnoreCase(value);
            case "!=" -> path.containsIgnoreCase(value).not();
            default -> throw new BadOperator("Bad operator for string field");
        };
    }


    protected BooleanExpression getNumberExpression(String field, FilterOperator operator, Class<?> type) throws BadOperator {


        if (Long.class.isAssignableFrom(type)){

            NumberPath<Long> path = entityPath.getNumber(field, Long.class);

            return getNumberPathBooleanExpression(path, operator);

        }else if (Integer.class.isAssignableFrom(type)){

            NumberPath<Integer> path = entityPath.getNumber(field, Integer.class);

            return getNumberPathBooleanExpression(path, operator);

        }else if (Double.class.isAssignableFrom(type)){

            NumberPath<Double> path = entityPath.getNumber(field, Double.class);

            return getNumberPathBooleanExpression(path, operator);

        }else{
            throw new BadOperator("Bad operator for number field");
        }

    }

    protected <T extends Number & Comparable<T>> BooleanExpression getNumberPathBooleanExpression(NumberPath<T> path, FilterOperator operator) throws BadOperator {

        T value;

        if (Long.class.isAssignableFrom(path.getType())) {
            value = (T) Long.valueOf(operator.getValue());
        } else if (Integer.class.isAssignableFrom(path.getType())) {
            value = (T) Integer.valueOf(operator.getValue());
        } else if (Double.class.isAssignableFrom(path.getType())) {
            value = (T) Double.valueOf(operator.getValue());
        } else {
            throw new BadOperator("Unsupported number type");
        }

        return switch (operator.getOperator()) {
            case "=" -> path.eq(value);
            case ">" -> path.gt(value);
            case ">=" -> path.goe(value);
            case "<" -> path.lt(value);
            case "<=" -> path.loe(value);
            case "!=" -> path.ne(value);
            default -> throw new BadOperator("Bad operator for number field");
        };
    }



    protected BooleanExpression getDateExpression(String field, FilterOperator operator) throws BadOperator {

        DatePath<Date> path = entityPath.getDate(field, java.util.Date.class);

        return getDatePathBooleanExpression(path, operator);

    }

    protected BooleanExpression getDatePathBooleanExpression(DatePath<Date> path, FilterOperator operator) throws BadOperator {

        Date date = getDateFromString(operator.getValue());

        Date date24h = new Date(date.getTime() + 86400000);

        return switch (operator.getOperator()) {
            case "=" -> path.between(date, date24h);
            case ">" -> path.gt(date);
            case ">=" -> path.goe(date);
            case "<" -> path.lt(date);
            case "<=" -> path.loe(date);
            default -> throw new BadOperator("Bad operator for date field");
        };

    }

    protected BooleanExpression getBooleanExpression(String field, FilterOperator operator) throws BadOperator {

        BooleanPath path = entityPath.getBoolean(field);

        return getBooleanPathBooleanExpression(path, operator);

    }

    protected BooleanExpression getBooleanPathBooleanExpression(BooleanPath path, FilterOperator operator) throws BadOperator {

        boolean value = Boolean.parseBoolean(operator.getValue());

        System.out.println("boolean value: " + value);

        return switch (operator.getOperator()) {
            case "=" -> path.eq(value);
            case "!=" -> path.ne(value);
            default -> throw new BadOperator("Bad operator for boolean field");
        };

    }

    protected Date getDateFromString(String date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        Date dateObject = null;
        try {
            dateObject = format.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateObject;
    }


    /**
     * Determines whether the given string value represents a valid number.
     *
     * @param value the string to be checked
     * @return true if the value is a valid number, false otherwise
     */
    protected boolean isANumber(String value){
        System.out.println("isANumber: " + value);
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines whether the given string value represents a valid date.
     *
     * @param value the string to be checked
     * @return true if the value is a valid date, false otherwise
     */
    protected boolean isADate(String value){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Determines whether the given string value represents a valid boolean.
     *
     * @param value the string to be checked
     * @return true if the value is a valid boolean, false otherwise
     */
    protected boolean isABoolean(String value){

        return Boolean.parseBoolean(value);

    }


}
