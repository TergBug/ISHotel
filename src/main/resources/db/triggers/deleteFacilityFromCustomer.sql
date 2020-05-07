create trigger delete_facility_from_customer
    before delete
    on customers_facilities
    for each row
begin
    declare current_quantity int;
    select f.quantity into current_quantity from facilities f where f.id = OLD.facility_id;
    update facilities set quantity=current_quantity + OLD.quantity where id = OLD.facility_id;
end;