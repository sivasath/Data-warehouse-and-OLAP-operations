--part2.a
--List the number of patients who had “tumor” (disease description),
SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT
DS_ID FROM DISEASE WHERE DESCRIPTION='tumor');

--List the number of patients who had “tumor” (disease description),“leukemia” (disease type),
SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT
DS_ID FROM DISEASE WHERE DISEASE.TYPE='leukemia');


--“ALL” (disease name),
SELECT COUNT(P_ID) FROM CLINICAL_FACT WHERE DS_ID IN (SELECT
DS_ID FROM DISEASE WHERE DISEASE.NAME='ALL');

--part2-b
--List the types of drugs which have been applied to patients with “tumor”.
select type from drug where dr_id IN (SELECT DR_ID FROM CLINICAL_FACT WHERE
DS_ID IN (SELECT DS_ID FROM DISEASE WHERE DESCRIPTION='tumor'));

--part2-c
--For each sample of patients with “ALL”, list the mRNA values (expression) of probes in cluster id “00002” for each experiment with measure unit id = “001”.
SELECT S_ID FROM MICROARRAY_FACT WHERE PB_ID IN (select pb_id from probe
join gene_fact on uid_probe=UID_GENEFACT where cl_id ='2' );

select s_id from CLINICAL_FACT where ds_id in (select ds_id from disease where NAME='ALL');

SELECT COUNT(EXP) FROM MICROARRAY_FACT WHERE MICROARRAY_FACT.MU_ID='1'
AND MICROARRAY_FACT.S_ID IN (select s_id from CLINICAL_FACT where ds_id in (select ds_id
from disease where NAME='ALL')) AND MICROARRAY_FACT.PB_ID IN (select pb_id from probe
join gene_fact on uid_probe=UID_GENEFACT where cl_id ='2' );



