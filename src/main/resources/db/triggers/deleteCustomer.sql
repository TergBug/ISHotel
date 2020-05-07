create trigger delete_customer
    before delete
    on customers
    for each row
begin
    update rooms set state=0 where id = OLD.room_id;
end;