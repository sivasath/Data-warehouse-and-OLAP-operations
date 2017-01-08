
create table exp_fortest1 as
select * from TEST_SAMPLES where UID_TEST in (select INFO_GENE.UID_INFO from INFO_GENE) ;

create table exp_groupawithinfo as 
select * from GROUP_A where UID_PROBE in (select INFO_GENE.UID_INFO from INFO_GENE) ;

create table exp_groupbwithinfo as 
select * from GROUP_B where UID_PROBE in (select INFO_GENE.UID_INFO from INFO_GENE) ;
--find corerlation of these two
--repeat for groupb
--comp to find inclination
select count(exp) from exp_groupawithinfo where s_id='973218';



