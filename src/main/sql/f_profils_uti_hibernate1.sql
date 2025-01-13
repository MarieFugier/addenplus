USE [hibernate1]
GO

INSERT INTO [dbo].[f_profils_uti]
           ([PRO_CODE]
           ,[PRO_ACC_NOM]
           ,[PRO_PASSWORD])
     VALUES
           ('PRO1', 'BAL', '$2a$10$fwP/c2P.NPgsqYxDDzqYXO0H0mQlW3jqXxR51XvFEveVyFgJOL61a'),
		   ('PRO2', 'BAL1', '$2a$10$EI8rXiKA7oImKDOKt8NPnOMWnGfnb.Pa4O5heB.DaaROrTFK2YVR6')
GO
