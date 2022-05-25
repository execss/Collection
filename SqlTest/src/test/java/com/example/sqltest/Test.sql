SELECT `SId`,`CId`,score FROM `SC`;

SELECT `SId`,`CId`,score as sc01score FROM `SC` WHERE CId ="01";

SELECT * FROM `SC` WHERE CId ="02";

SELECT
    `SId` as sc01SId,
    `CId` as sc01CId,
    score as sc01score
FROM
    `SC`
WHERE
    CId = "01";

SELECT
    `SId` as sc02SId,
    `CId` as sc02CId,
    score as sc02score
FROM
    `SC`
WHERE
    CId = "02";


--========================================================================

--1   查询" 01 "课程比" 02 "课程成绩高的学生的信息及课程分数

--==========================================================================
SELECT
    *
FROM
    `Student` st
    JOIN `SC` sc ON st.SId = sc.SId
WHERE
    st.SId IN (
        SELECT
            sc01SId
        FROM
            (
                SELECT
                    `SId` as sc01SId,
                    `CId` as sc01CId,
                    score as sc01score
                FROM
                    `SC`
                WHERE
                    CId = "01"
            ) sc01
            JOIN (
                SELECT
                    `SId` as sc02SId,
                    `CId` as sc02CId,
                    score as sc02score
                FROM
                    `SC`
                WHERE
                    CId = "02"
            ) sc02 ON sc01.sc01SId = sc02.sc02SId
        WHERE
            sc01score > sc02score
    );

SELECT
    *
FROM
    (
        SELECT
            `SId` as sc01SId,
            `CId` as sc01CId,
            score as sc01score
        FROM
            `SC`
        WHERE
            CId = "01"
    ) sc01
    JOIN (
        SELECT
            `SId` as sc02SId,
            `CId` as sc02CId,
            score as sc02score
        FROM
            `SC`
        WHERE
            CId = "02"
    ) sc02 ON sc01.sc01SId = sc02.sc02SId;

--========================================================================ADD

--1.1 查询存在" 01 "课程但可能不存在" 02 "课程的情况(不存在时显示为 null )

--==========================================================================

SELECT
    *
FROM
    (
        SELECT
            `SId` as sc01SId,
            `CId` as sc01CId,
            score as sc01score
        FROM
            `SC`
        WHERE
            CId = "01"
    ) sc01
    LEFT JOIN (
        SELECT
            `SId` as sc02SId,
            `CId` as sc02CId,
            score as sc02score
        FROM
            `SC`
        WHERE
            CId = "02"
    ) sc02 ON sc01.sc01SId = sc02.sc02SId;

--========================================================================ADD

-- 1.2 查询存在" 01 "课程但可能不存在" 02 "课程的情况(不存在时显示为 null )

--==========================================================================