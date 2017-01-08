
  --Group A
  
 create table task3 as;
 select (uid_probe),pb_id from probe where pb_id in (select pb_id from MICROARRAY_FACT where s_id in 
    (select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where  NAME='ALL')));
    
create table task31 as ;
select PB_ID,s_id,exp from MICROARRAY_FACT
where s_id in (select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where  NAME='ALL'));

select task31.pb_id,task31.s_id,task31.exp from TASK31 join task3 on TASK31.PB_ID=task3.PB_ID;

 create table group_a as select task3.UID_PROBE,task31.s_id,task31.exp from TASK31 join task3 on TASK31.PB_ID=task3.PB_ID;
--Group B

create table task_3B as select (uid_probe),pb_id from probe where pb_id in (select pb_id from MICROARRAY_FACT where s_id in 
    (select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where not NAME='ALL')));
    
create table task_31B as select PB_ID,s_id,exp from MICROARRAY_FACT
where s_id in (select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where not NAME='ALL'));

create TABLE group_b as select task_3B.UID_PROBE,task_31B.s_id,task_31B.exp from TASK_31B join task_3B on TASK_31B.PB_ID=task_3B.PB_ID;

select (task_3B.UID_PROBE) from TASK_31B join task_3B on TASK_31B.PB_ID=task_3B.PB_ID;


--create table uid_uniqueas
select count( uid_genefact) from GENE_FACT;

select exp from group_a where uid_probe='2616922';