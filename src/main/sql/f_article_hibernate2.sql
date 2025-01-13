USE [hibernate2]
GO

INSERT INTO [dbo].[f_article]
           ([art_code]
           ,[art_cee_code]
           ,[art_cei_code]
           ,[art_cle_code]
           ,[art_code_barre]
           ,[art_geo_code])
     VALUES
           ('ART1','','','','123', 'GEO123'),
           ('ART2','','','','567', 'GEO567')
GO