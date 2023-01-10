package com.bgsystem.bugtracker.shared.models.listRequest;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

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

        BooleanExpression expression = null;

        if (entityFields.contains(field)){
            expression = this.getCustomPathExpression(filter);
        }else{
            expression = this.commonExpressionsSeparator(filter);
        }

        return expression;

    }

    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {
        return null;
    }


    protected BooleanExpression commonExpressionsSeparator(FilterRequest filter) throws BadOperator {

        BooleanExpression expression = null;

        String field = filter.getField();

        for (FilterOperator operator : filter.getOperations()){

            if (isANumber(operator.getValue())){

                expression = this.addOrExpression(expression, this.getNumberExpression(field, operator));

            }else if (isADate(operator.getValue())){

                expression = this.addOrExpression(expression, this.getDateExpression(field, operator));

            }else if (isABoolean(operator.getValue())) {

                expression = this.addOrExpression(expression, this.getBooleanExpression(field, operator));

            }else{

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

        switch (operator.getOperator()) {
            case ":":
                return path.containsIgnoreCase(value);
            case "=":
                return path.equalsIgnoreCase(value);
            case "!=":
                return path.containsIgnoreCase(value).not();
            default:
                throw new BadOperator("Bad operator for string field");
        }
    }


    protected BooleanExpression getNumberExpression(String field, FilterOperator operator) throws BadOperator {

        NumberPath path = entityPath.getNumber(field, Long.class);

        return getNumberPathBooleanExpression(path, operator);

    }

    protected BooleanExpression getNumberPathBooleanExpression(NumberPath path, FilterOperator operator) throws BadOperator{

        //If statement to check if the path class is java.lang.Long
        if (path.getType().equals(Long.class)){

            Long value = operator.getValue() == null ? null : Long.parseLong(operator.getValue());

            switch (operator.getOperator()) {
                case "=":
                    return path.eq(value);
                case ">":
                    return path.gt(value);
                case ">=":
                    return path.goe(value);
                case "<":
                    return path.lt(value);
                case "<=":
                    return path.loe(value);
                case "!=":
                    return path.ne(value);
                default:
                    throw new BadOperator("Bad operator for number field");
            }

        } else if (path.getType().equals(Double.class)) {

            double value = Double.parseDouble(operator.getValue());

            switch (operator.getOperator()) {
                case "=":
                    return path.eq(value);
                case ">":
                    return path.gt(value);
                case ">=":
                    return path.goe(value);
                case "<":
                    return path.lt(value);
                case "<=":
                    return path.loe(value);
                case "!=":
                    return path.ne(value);
                default:
                    throw new BadOperator("Bad operator for number field");
            }
        }

        return null;

    }


    protected BooleanExpression getDateExpression(String field, FilterOperator operator) throws BadOperator {

        DatePath path = entityPath.getDate(field, java.util.Date.class);

        return getDatePathBooleanExpression(path, operator);

    }

    protected BooleanExpression getDatePathBooleanExpression(DatePath path, FilterOperator operator) throws BadOperator {

        Date date = getDateFromString(operator.getValue());

        Date date24h = new Date(date.getTime() + 86400000);

        switch (operator.getOperator()) {
            case "=":
                return path.between(date, date24h);
            case ">":
                return path.gt(date);
            case ">=":
                return path.goe(date);
            case "<":
                return path.lt(date);
            case "<=":
                return path.loe(date);
            default:
                throw new BadOperator("Bad operator for date field");
        }

    }

    protected BooleanExpression getBooleanExpression(String field, FilterOperator operator) throws BadOperator {

        BooleanPath path = entityPath.getBoolean(field);

        return getBooleanPathBooleanExpression(path, operator);

    }

    protected BooleanExpression getBooleanPathBooleanExpression(BooleanPath path, FilterOperator operator) throws BadOperator {

        boolean value = Boolean.parseBoolean(operator.getValue());

        System.out.println("boolean value: " + value);

        switch (operator.getOperator()) {
            case "=":
                return path.eq(value);
            case "!=":
                return path.ne(value);
            default:
                throw new BadOperator("Bad operator for boolean field");
        }

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

        if (Boolean.parseBoolean(value)){
            return true;
        }else{
            return false;
        }
    }


}
