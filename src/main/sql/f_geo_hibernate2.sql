USE [hibernate2]
GO

INSERT INTO [dbo].[f_geographique]
           ([geo_code]
           ,[geo_bat_code]
           ,[geo_etage]
           ,[geo_haut]
           ,[geo_larg]
           ,[geo_long]
           ,[geo_numpiece]
           ,[geo_surf]
           ,[geo_code_bar])
     VALUES
           ('GEO123', '', '', 0, 0, 0, '', 0, 'GEO123'),
           ('GEO567', '', '', 0, 0, 0, '', 0, 'GEO567')
GO


