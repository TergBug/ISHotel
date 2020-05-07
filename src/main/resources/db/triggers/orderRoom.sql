create trigger order_room
    before insert
    on customers
    for each row
begin
    if (select r.state from rooms r where r.id = NEW.room_id) = 0 then
        update rooms set state=1 where id = NEW.room_id;
    else
        signal sqlstate '45000' set message_text = 'This room is not free';
    end if;
end;