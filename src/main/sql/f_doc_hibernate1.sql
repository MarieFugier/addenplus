USE [hibernate1]
GO

INSERT INTO [dbo].[f_documents]
           ([DOC_CODE]
           ,[DOC_DEFAULT]
           ,[DOC_PARENT]
           ,[DOC_PATH]
           ,[DOC_TITLE])
     VALUES
           ('DOC1', 1, 'ART1', 'C:/DEV/JAVA_17_stage/API_doc_images/Chaise1.jpg', 'Chaise 1'),
           ('DOC2', 1, 'ART2', 'C:/DEV/JAVA_17_stage/API_doc_images/Chaise2.jpg', 'Chaise 2')
GO

