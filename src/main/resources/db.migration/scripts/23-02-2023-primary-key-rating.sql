--liquibase formatted sql
--changeset andrei.munteanu:primary_key_rating splitStatement:true andDelimiter:;
ALTER TABLE rating ADD PRIMARY KEY (hike_id, user_id);