#!/usr/bin/env ruby

class PasswordChecker
    def password_passes_no_duplicates(password)
        parts = password.split(" ")
        alreadySeen = []

        parts.each do |part|
            if alreadySeen.include?(part)
                return false
            else
                alreadySeen << part
            end
        end
        
        return true
    end
    
    def password_passes_no_anagrams(password)
        parts = password.split(" ")
        alreadySeen = []

        parts.each do |part|
            if alreadySeen.include?(part.chars.sort(&:casecmp).join)
                return false
            else
                alreadySeen << part.chars.sort(&:casecmp).join
            end
        end
        
        return true
    end
    
    def count_valid_passwords(password_list)
    
        validCount = 0
        
        password_list.each do |password|
            if password_passes_no_duplicates(password)
                validCount = validCount + 1
            end
        end
        
        return validCount
    end
    
    def count_valid_passwords_2(password_list)
    
        validCount = 0
        
        password_list.each do |password|
            if password_passes_no_anagrams(password)
                validCount = validCount + 1
            end
        end
        
        return validCount
    end
    
end

if __FILE__ == $0
    pc = PasswordChecker.new
    
    puts "Running checks for part 1"
    valid = pc.password_passes_no_duplicates("aa bb cc dd ee")
    raise "Failed - output of 'aa bb cc dd ee' should have been true" unless valid
    
    valid = pc.password_passes_no_duplicates("aa bb cc dd aa")
    raise "Failed - output of 'aa bb cc dd aa' should have been false" unless not valid
    
    valid = pc.password_passes_no_duplicates("aa bb cc dd aaa")
    raise "Failed - output of 'aa bb cc dd aaa' should have been true" unless valid
    
    
    count = pc.count_valid_passwords(["aa bb cc dd ee","aa bb cc dd aa","aa bb cc dd aaa"])
    raise "Only 2 passwords should have been valid" unless count == 2
    
    passwords = File.readlines('passwords.txt')
    count = pc.count_valid_passwords(passwords)
    
    puts "Evaluated part 1, found #{count} valid passwords"
    
    puts "Running checks for part 2"
    valid = pc.password_passes_no_anagrams("aa bb cc dd ee")
    raise "Failed - output of 'aa bb cc dd ee' should have been true" unless valid
    
    valid = pc.password_passes_no_anagrams("aa bb cc dd aa")
    raise "Failed - output of 'aa bb cc dd aa' should have been false" unless not valid
    
    valid = pc.password_passes_no_anagrams("dave bb cc dd evad")
    raise "Failed - output of 'dave bb cc dd evad' should have been true" unless not valid
    
    valid = pc.password_passes_no_anagrams("dave bb cc dd dave")
    raise "Failed - output of 'dave bb cc dd dave' should have been true" unless not valid
    
    count = pc.count_valid_passwords_2(passwords)
    
    puts "Evaluated part 2, found #{count} valid passwords"
    
    puts "Finished successfully"
end
