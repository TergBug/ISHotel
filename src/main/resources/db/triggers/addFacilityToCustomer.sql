create trigger add_facility_to_customer
    before insert
    on customers_facilities
    for each row
begin
    declare current_quantity int;
    select f.quantity into current_quantity from facilities f where f.id = NEW.facility_id;
    if NEW.quantity <= current_quantity then
        update facilities set quantity=current_quantity - NEW.quantity where id = NEW.facility_id;
    else
        signal sqlstate '45000' set message_text = 'You can not insert record';
    end if;
end;