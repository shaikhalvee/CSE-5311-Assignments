import random
import string
import time

import matplotlib.pyplot as plt


def naive_search(text, pattern):
    occurrences = []
    n = len(text)
    m = len(pattern)
    for s in range(n - m + 1):
        if text[s:s + m] == pattern:
            occurrences.append(s)
    return occurrences


def rabin_karp_search(text, pattern, d=256, q=101):
    occurrences = []
    n = len(text)
    m = len(pattern)
    h = pow(d, m - 1) % q
    p = 0  # Hash value for pattern
    t = 0  # Hash value for text
    for i in range(m):
        p = (d * p + ord(pattern[i])) % q
        t = (d * t + ord(text[i])) % q
    for s in range(n - m + 1):
        if p == t:
            if text[s:s + m] == pattern:
                occurrences.append(s)
        if s < n - m:
            t = (t - h * ord(text[s])) % q
            t = (t * d + ord(text[s + m])) % q
            t = (t + q) % q  # Ensure t >= 0
    return occurrences


def compute_lps(pattern):
    m = len(pattern)
    lps = [0] * m
    length = 0  # Length of the previous longest prefix suffix
    i = 1
    while i < m:
        if pattern[i] == pattern[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1
    return lps


def kmp_search(text, pattern):
    occurrences = []
    n = len(text)
    m = len(pattern)
    lps = compute_lps(pattern)
    i = j = 0  # Index for text and pattern
    while i < n:
        if text[i] == pattern[j]:
            i += 1
            j += 1
        if j == m:
            occurrences.append(i - j)
            j = lps[j - 1]
        elif i < n and text[i] != pattern[j]:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1
    return occurrences


def build_transition_table(pattern):
    m = len(pattern)
    alphabet = set(pattern)
    transition_table = [{} for _ in range(m + 1)]
    for q in range(m + 1):
        for a in alphabet:
            k = min(m, q + 1)
            while True:
                if pattern[:k] == (pattern[:q] + a)[-k:]:
                    break
                k -= 1
            transition_table[q][a] = k
    return transition_table


def finite_automata_search(text, pattern):
    occurrences = []
    n = len(text)
    m = len(pattern)
    transition_table = build_transition_table(pattern)
    q = 0
    for i in range(n):
        q = transition_table[q].get(text[i], 0)
        if q == m:
            occurrences.append(i - m + 1)
    return occurrences


def build_suffix_array(text):
    n = len(text)
    suffixes = [(text[i:], i) for i in range(n)]
    suffixes.sort()
    sa = [suffix[1] for suffix in suffixes]
    return sa


def suffix_array_search(text, pattern, sa):
    n = len(text)
    m = len(pattern)
    occurrences = []
    left = 0
    right = n - 1
    while left <= right:
        mid = (left + right) // 2
        substr = text[sa[mid]:sa[mid] + m]
        if pattern == substr:
            occurrences.append(sa[mid])
            # Search adjacent entries for multiple occurrences
            l = mid - 1
            while l >= left and text[sa[l]:sa[l] + m] == pattern:
                occurrences.append(sa[l])
                l -= 1
            r = mid + 1
            while r <= right and text[sa[r]:sa[r] + m] == pattern:
                occurrences.append(sa[r])
                r += 1
            break
        elif pattern < substr:
            right = mid - 1
        else:
            left = mid + 1
    return sorted(occurrences)


def generate_text_and_pattern(n, m):
    text = ''.join(random.choices(string.ascii_lowercase, k=n))
    start_index = random.randint(0, n - m)
    pattern = text[start_index:start_index + m]
    return text, pattern


def benchmark_algorithms(input_sizes):
    naive_times = []
    rabin_karp_times = []
    kmp_times = []
    fa_times = []
    sa_times = []
    pattern_length = 5  # Fixed pattern length for consistency

    for n in input_sizes:
        print(f"Benchmarking for input size: {n}")
        text, pattern = generate_text_and_pattern(n, pattern_length)

        # Naive Approach
        start_time = time.time()
        naive_search(text, pattern)
        naive_times.append(time.time() - start_time)

        # Rabin-Karp Algorithm
        start_time = time.time()
        rabin_karp_search(text, pattern)
        rabin_karp_times.append(time.time() - start_time)

        # KMP Algorithm
        start_time = time.time()
        kmp_search(text, pattern)
        kmp_times.append(time.time() - start_time)

        # Finite Automata-Based Matching
        start_time = time.time()
        finite_automata_search(text, pattern)
        fa_times.append(time.time() - start_time)

        # Suffix Arrays
        start_time = time.time()
        sa = build_suffix_array(text)
        suffix_array_search(text, pattern, sa)
        sa_times.append(time.time() - start_time)

    return {
        'input_sizes': input_sizes,
        'naive': naive_times,
        'rabin_karp': rabin_karp_times,
        'kmp': kmp_times,
        'finite_automata': fa_times,
        'suffix_array': sa_times
    }


def plot_results(results):
    plt.figure(figsize=(10, 6))
    plt.plot(results['input_sizes'], results['naive'], label='Naive', marker='o')
    plt.plot(results['input_sizes'], results['rabin_karp'], label='Rabin-Karp', marker='o')
    plt.plot(results['input_sizes'], results['kmp'], label='KMP', marker='o')
    plt.plot(results['input_sizes'], results['finite_automata'], label='Finite Automata', marker='o')
    plt.plot(results['input_sizes'], results['suffix_array'], label='Suffix Array', marker='o')
    plt.xlabel('Input Text Size (n)')
    plt.ylabel('Execution Time (seconds)')
    plt.title('Benchmarking String Matching Algorithms')
    plt.legend()
    plt.grid(True)
    plt.show()


if __name__ == "__main__":
    input_sizes = [10, 1000, 10000, 100000, 1000000, 10000000]
    benchmark_results = benchmark_algorithms(input_sizes)
    plot_results(benchmark_results)
