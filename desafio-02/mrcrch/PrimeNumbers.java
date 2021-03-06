import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class PrimeNumbers {

    private static final Collection<Long> generate(final long min, final long max) {

        final Collection<Long> primeNumbers = new LinkedHashSet<>();
        final Collection<Long> compositeNumbers = new HashSet<>();

        // 2, por definição, é primo
        if (min <= 2 && 2 <= max) {
            primeNumbers.add(2L);
        }

        // Inicia os cálculos do 3 ou do menor número ímpar maior que o mínimo informado
        long start = 3;
        if (min > 3) {

            start = min;

            // Caso o valor mínimo seja par, o incrementa
            if (min % 2 == 0) {
                start++;
            }

        }

        // Sempre incrementa de dois em dois pois números pares (exceto o 2) não são primos
        for (long numberToCheck = start; numberToCheck <= max; numberToCheck += 2) {

            // Um múltiplo do número atual nunca será primo
            compositeNumbers.add(numberToCheck * 2);

            // Verifica se é um número composto conhecido
            if (compositeNumbers.contains(numberToCheck)) {
                continue;
            }

            // Verificação básica: caso seja um número maior que 10 e divisível por 2 ou com final 5/0, não será primo
            if (numberToCheck > 10) {
                long remainder = numberToCheck % 10;
                if (remainder % 2 == 0 || remainder == 0 || remainder == 5) {
                    continue;
                }
            }

            // Verifica se possui algum divisor que não seja 1 e ele mesmo
            boolean isPrime = true;
            for (long i = 2; i < numberToCheck / 2; i++) {

                if (numberToCheck % i == 0) {
                    isPrime = false;
                    break;
                }

            }

            if (isPrime) {
                primeNumbers.add(numberToCheck);
            }

        }

        return primeNumbers;
    }

    public static void main(final String[] args) {

        if (args == null || args.length != 2) {
            throw new IllegalArgumentException(
                "Quantidade de parâmetros inválida. São esperados 2 parâmetro (min/max)");
        }

        String paramMin = args[0];
        String paramMax = args[1];

        long min = 0;
        long max = 0;

        try {
            min = Long.valueOf(paramMin);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Parâmetro mínimo não é um número válido: " + paramMin);
        }

        try {
            max = Long.valueOf(paramMax);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Parâmetro máximo não é um número válido: " + paramMax);
        }

        if (min < 0) {
            throw new IllegalArgumentException("Valor mínimo não pode ser negativo: " + min);
        }

        if (max < 0) {
            throw new IllegalArgumentException("Valor máximo não pode ser negativo: " + max);
        }

        if (max <= min) {
            throw new IllegalArgumentException("Valor máximo deve ser maior que o valor mínimo: " + min + "/" + max);
        }

        Collection<Long> primes = generate(min, max);
        System.out.println(primes.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", ")));

    }

}
