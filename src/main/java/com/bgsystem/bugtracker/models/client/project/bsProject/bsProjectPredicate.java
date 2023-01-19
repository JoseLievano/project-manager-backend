package com.bgsystem.bugtracker.models.client.project.bsProject;

import com.bgsystem.bugtracker.exeptions.BadOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.CommonPathExpression;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterOperator;
import com.bgsystem.bugtracker.shared.models.listRequest.FilterRequest;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class bsProjectPredicate extends CommonPathExpression<bsProjectEntity> {

    private final QbsProjectEntity bsProjectEntity = QbsProjectEntity.bsProjectEntity;

    public bsProjectPredicate(){
        super( );
        this.entityPath = new PathBuilder<bsProjectEntity>(bsProjectEntity.class, "bsProjectEntity");
        this.entityFields.add("business");
        this.entityFields.add("client");
        this.entityFields.add("tasks");
        this.entityFields.add("invoices");
        this.entityFields.add("channels");
        this.entityFields.add("docsCategories");
        this.entityFields.add("docs");
        this.entityFields.add("kbCategories");
        this.entityFields.add("kbs");

    }

    @Override
    protected BooleanExpression getCustomPathExpression(FilterRequest filter) throws BadOperator {

        return switch (filter.getField()){
            case "business" -> getBusinessExpression(filter);
            case "client" -> getClientExpression(filter);
            case "tasks" -> getTasksExpression(filter);
            case "invoices" -> getInvoicesExpression(filter);
            case "channels" -> getChannelsExpression(filter);
            case "docsCategories" -> getDocsCategoriesExpression(filter);
            case "docs" -> getDocsExpression(filter);
            case "kbCategories" -> getKbCategoriesExpression(filter);
            case "kbs" -> getKbsExpression(filter);
            default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
        };

    }

    private BooleanExpression getKbsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression kbsExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (filter.getField()){
                case "id" -> {
                    NumberPath<Long> id = bsProjectEntity.kbs.any().id;
                    kbsExpression = addOrExpression(kbsExpression, getNumberPathBooleanExpression(id, operation));
                }
                case "title" -> {
                    StringPath title = bsProjectEntity.kbs.any().title;
                    kbsExpression = addOrExpression(kbsExpression, getStringPathBooleanExpression(title, operation));
                }
                case "content" -> {
                    StringPath content = bsProjectEntity.kbs.any().content;
                    kbsExpression = addOrExpression(kbsExpression, getStringPathBooleanExpression(content, operation));
                }
                default -> throw new BadOperator("Bad operator: " + operation.getOperator());
            }
        }

        return kbsExpression;

    }

    private BooleanExpression getKbCategoriesExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression kbCategoriesExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.kbCategories.any().id;
                    kbCategoriesExpression = addOrExpression(kbCategoriesExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsProjectEntity.kbCategories.any().name;
                    kbCategoriesExpression = addOrExpression(kbCategoriesExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return kbCategoriesExpression;

    }

    private BooleanExpression getDocsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression docsExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.docs.any().id;
                    docsExpression = addOrExpression(docsExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "title" -> {
                    StringPath titlePath = bsProjectEntity.docs.any().title;
                    docsExpression = addOrExpression(docsExpression, getStringPathBooleanExpression(titlePath, operation));
                }
                case "content" -> {
                    StringPath contentPath = bsProjectEntity.docs.any().content;
                    docsExpression = addOrExpression(docsExpression, getStringPathBooleanExpression(contentPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return docsExpression;

    }

    private BooleanExpression getDocsCategoriesExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression docsCategoriesExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.docsCategories.any().id;
                    docsCategoriesExpression = addOrExpression(docsCategoriesExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsProjectEntity.docsCategories.any().name;
                    docsCategoriesExpression = addOrExpression(docsCategoriesExpression, getStringPathBooleanExpression(namePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return docsCategoriesExpression;

    }

    private BooleanExpression getChannelsExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression channelsExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.channels.any().id;
                    channelsExpression = addOrExpression(channelsExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsProjectEntity.channels.any().name;
                    channelsExpression = addOrExpression(channelsExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "isPublic" -> {
                    BooleanPath isPublicPath = bsProjectEntity.channels.any().isPublic;
                    channelsExpression = addOrExpression(channelsExpression, getBooleanPathBooleanExpression(isPublicPath, operation));
                }
                case "description" -> {
                    StringPath descriptionPath = bsProjectEntity.channels.any().description;
                    channelsExpression = addOrExpression(channelsExpression, getStringPathBooleanExpression(descriptionPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return channelsExpression;

    }

    private BooleanExpression getInvoicesExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression invoicesExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.invoices.any().id;
                    invoicesExpression = addOrExpression(invoicesExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "amount" -> {
                    NumberPath<Double> amountPath = bsProjectEntity.invoices.any().amount;
                    invoicesExpression = addOrExpression(invoicesExpression, getNumberPathBooleanExpression(amountPath, operation));
                }
                case "isPaid" -> {
                    BooleanPath isPaidPath = bsProjectEntity.invoices.any().isPaid;
                    invoicesExpression = addOrExpression(invoicesExpression, getBooleanPathBooleanExpression(isPaidPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsProjectEntity.invoices.any().isOverDue;
                    invoicesExpression = addOrExpression(invoicesExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "number" -> {
                    StringPath numberPath = bsProjectEntity.invoices.any().number;
                    invoicesExpression = addOrExpression(invoicesExpression, getStringPathBooleanExpression(numberPath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return invoicesExpression;

    }

    private BooleanExpression getTasksExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression taskExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.tasks.any().id;
                    taskExpression = addOrExpression(taskExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsProjectEntity.tasks.any().name;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "description" -> {
                    StringPath descriptionPath = bsProjectEntity.tasks.any().description;
                    taskExpression = addOrExpression(taskExpression, getStringPathBooleanExpression(descriptionPath, operation));
                }
                case "isInternal" -> {
                    BooleanPath isInternalPath = bsProjectEntity.tasks.any().isInternal;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isInternalPath, operation));
                }
                case "isOverDue" -> {
                    BooleanPath isOverDuePath = bsProjectEntity.tasks.any().isOverDue;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isOverDuePath, operation));
                }
                case "isDone" -> {
                    BooleanPath isDonePath = bsProjectEntity.tasks.any().isDone;
                    taskExpression = addOrExpression(taskExpression, getBooleanPathBooleanExpression(isDonePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return taskExpression;

    }

    private BooleanExpression getClientExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression clientExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (filter.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.client.id;
                    clientExpression = addOrExpression(clientExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "firstName" -> {
                    StringPath firstNamePath = bsProjectEntity.client.firstName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(firstNamePath, operation));
                }
                case "lastName" -> {
                    StringPath lastNamePath = bsProjectEntity.client.lastName;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(lastNamePath, operation));
                }
                case "email" -> {
                    StringPath emailPath = bsProjectEntity.client.email;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(emailPath, operation));
                }
                case "username" -> {
                    StringPath usernamePath = bsProjectEntity.client.username;
                    clientExpression = addOrExpression(clientExpression, getStringPathBooleanExpression(usernamePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + filter.getField());
            }
        }

        return clientExpression;

    }

    private BooleanExpression getBusinessExpression(FilterRequest filter) throws BadOperator {

        BooleanExpression businessExpression = null;

        for (FilterOperator operation : filter.getOperations()){
            switch (operation.getField()){
                case "id" -> {
                    NumberPath<Long> idPath = bsProjectEntity.business.id;
                    businessExpression = addOrExpression(businessExpression, getNumberPathBooleanExpression(idPath, operation));
                }
                case "name" -> {
                    StringPath namePath = bsProjectEntity.business.name;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(namePath, operation));
                }
                case "taxID" -> {
                    StringPath taxIDPath = bsProjectEntity.business.taxID;
                    businessExpression = addOrExpression(businessExpression, getStringPathBooleanExpression(taxIDPath, operation));
                }
                case "dateCreated" -> {
                    DatePath<Date> dateCreatedPath = entityPath.getDate(bsProjectEntity.business.dateCreated.toString(), java.util.Date.class);
                    businessExpression = addOrExpression(businessExpression, getDatePathBooleanExpression(dateCreatedPath, operation));
                }
                case "pendingInvoice" -> {
                    BooleanPath pendingInvoicePath = bsProjectEntity.business.pendingInvoice;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(pendingInvoicePath, operation));
                }
                case "overDue" -> {
                    BooleanPath overDuePath = bsProjectEntity.business.overDue;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(overDuePath, operation));
                }
                case "isActive" -> {
                    BooleanPath isActivePath = bsProjectEntity.business.isActive;
                    businessExpression = addOrExpression(businessExpression, getBooleanPathBooleanExpression(isActivePath, operation));
                }
                default -> throw new IllegalArgumentException("Illegal field: " + operation.getField());
            }
        }

        return businessExpression;

    }
}
