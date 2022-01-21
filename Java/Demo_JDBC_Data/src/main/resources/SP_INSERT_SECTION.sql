delimiter $$

create procedure SP_INSERT_SECTION
(in id int, in section_name varchar(50), in delegate_id int)
begin
    insert into section values(id, section_name, delegate_id)
end$$

delimiter ;
