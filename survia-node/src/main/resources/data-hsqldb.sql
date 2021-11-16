-- views

CREATE VIEW AGGREGATED_OBSERVATIONS_V ( TOTAL, INDICATOR_ID ) AS
    select sum(`O`.`VALUE`) AS `TOTAL`, `O`.`INDICATOR_ID` AS `INDICATOR_ID`
    from `OBSERVATIONS` `O`
    where `O`.`TIMESTAMP` = (
        select max(`O2`.`TIMESTAMP`)
        from `OBSERVATIONS` `O2` JOIN `INDICATORS` I ON `O2`.`INDICATOR_ID` = `I`.`ID`
        where `O2`.`UDID` = `O`.`UDID`
        and `O2`.`INDICATOR_ID` = `O`.`INDICATOR_ID`
        and `I`.`CODED` = false
    )
    group by `O`.`INDICATOR_ID`;



-- TODO view with "count" for coded Indicators (CODED = true)